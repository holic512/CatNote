// * Description: axios实例
// * Version: 1.0

import axios from 'axios';
import {tokenStore} from "../pinia/token.ts";
import {routerStore} from "../pinia/routerStore.ts";

// 路由实例
import router from "../router/index.ts"


// 创建一个 Axios 实例
const instance = axios.create({
    baseURL: 'http://localhost:8080/', // 这里是你的基础 URL
    timeout: 10000, // 可选的超时时间
    headers: {'Content-Type': 'application/json'}
});

// 创建请求拦截器
instance.interceptors.request.use(config => {
    const token = tokenStore().getToken();
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
}, error => {
    return Promise.reject(error);
});

// 响应拦截器
instance.interceptors.response.use(response => {
        return response;
    }, error => {

        // 判断当前是 admin 还是 user 页面，进行相应的跳转
        const currentRoute = router.currentRoute.value;
        // 判断是 在 切换路由 true:交给路由守卫处理 false 自己处理
        if (!routerStore().getGuard()) {
            if (error.response && error.response.status === 401) {
                // 未登录或token失效
                tokenStore().clearToken(); // 清理原 token

                if (currentRoute.path.startsWith('/admin')) {
                    // 重定向到 admin 的登录页面
                    router.push('/admin/auth/login');
                } else {
                    // 重定向到 user 的登录页面
                    router.push('/user/auth/login');
                }

            } else if (error.response && error.response.status === 403) {
                // 权限不符合
                tokenStore().clearToken();
                router.push('/pd'); // 权限错误页面
            }
        }
        return Promise.reject(error);
    }
);

export default instance;
