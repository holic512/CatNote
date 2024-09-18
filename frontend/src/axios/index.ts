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
    headers: {"Content-Type": "application/json",}
});


// 创建请求拦截器
instance.interceptors.request.use(config => {
    // 判断是 在 切换路由 true:交给路由守卫处理 false 自己处理

    if (!routerStore().getGuard()) {
        // 获取当前路由的路径
        const getCurrentRoute = () => router.currentRoute.value.path;

        const UserToken = tokenStore().getUserToken();
        const AdminToken = tokenStore().getAdminToken();
        if (UserToken && getCurrentRoute().startsWith('/user')) {
            config.headers.satoken = UserToken;
        }
        if (AdminToken && getCurrentRoute().startsWith('/admin')) {
            config.headers.satoken = AdminToken;
        }
    }
    return config;
}, error => {
    return Promise.reject(error);
});

// 响应拦截器
instance.interceptors.response.use(response => {
        return response;
    }, error => {
        // 判断是 在 切换路由 true:交给路由守卫处理 false 自己处理
        if (!routerStore().getGuard()) {
            if (error.response && error.response.status === 401) {
                // 判断当前是 admin 还是 user 页面，进行相应的跳转
                const currentRoute = router.currentRoute.value;
                if (currentRoute.path.startsWith('/admin')) {
                    // 清理原 token
                    tokenStore().clearAdminToken()
                    // 重定向到 admin 的登录页面
                    router.push('/admin/auth/login');
                } else {
                    // 清理原 token
                    tokenStore().clearUserToken()
                    // 重定向到 user 的登录页面
                    router.push('/user/auth/login');
                }

            } else if (error.response && error.response.status === 403) {
                router.push('/pd'); // 权限错误页面
            }
        }
        return Promise.reject(error);
    }
);

export default instance;
