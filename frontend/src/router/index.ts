// router.ts

import {createRouter, createWebHistory} from 'vue-router';
import homeRoutes from '../views/Home/router.ts'
import adminRoutes from '../views/Admin/router.ts'
import userRouters from '../views/User/router.ts'
import {tokenStore} from "../pinia/token.ts";
import axios from "../axios";
import {routerStore} from "../pinia/routerStore.ts";
import {ElMessage} from "element-plus";


const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        ...homeRoutes,
        ...userRouters,
        ...adminRoutes,

    ]
});

router.beforeEach(async (to, from, next) => {
    const token = tokenStore().getToken();
    // 检查是否需要登录
    if (to.meta.requiresAuth) {
        if (!token) {
            // 没有 token，重定向到登录页面
            ElMessage["warning"]('您还未登录,请登录')
            if (to.path.startsWith('/admin')) {
                next('/admin/auth/login'); // Admin 登录页面
            } else {
                next('/user/auth/login'); // User 登录页面
            }
            return; // 确保后续代码不再执行
        }

        // 开启路由守卫 阻止响应拦截器操作页面
        routerStore().onGuard()

        try {
            // 有 token，访问后端, 获取有效性和权限
            const response = await axios.get("auth/validate");
            const rule = response.data;

            if (to.path.startsWith('/admin')) {
                if (rule !== "[ROLE_ADMIN]") {
                    tokenStore().clearToken();
                    next('/pd'); // 权限错误页面
                } else {
                    next(); // 继续访问目标路由
                }
            } else {
                if (rule !== "[ROLE_USER]") {
                    tokenStore().clearToken();
                    next('/pd'); // 权限错误页面
                } else {
                    next(); // 继续访问目标路由
                }
            }
        } catch (error) {
            // token已失效或其他错误


            if (error.response?.status === 401) {
                //清理错误的或失效的 token
                tokenStore().clearToken();

                ElMessage["warning"]('您还未登录或登录权限已过期,请重新登录')
                if (to.path.startsWith('/admin')) {
                    next('/admin/auth/login'); // Admin 登录页面
                } else {
                    next('/user/auth/login'); // User 登录页面
                }
            } else if (error.response?.status === 403) {
                //清理错误的或失效的 token
                tokenStore().clearToken();

                ElMessage["warning"]('此权限不正确,请重新登录')
                // 其他错误处理 403
                next('/pd'); // 权限错误页面
            } else {
                ElMessage["warning"]('请检查网络是否连接')
                next('/'); // 权限错误页面
            }
        }

        // 关闭路由守卫 阻止响应拦截器操作页面
        routerStore().offGuard()

    } else {
        // 不需要登录，直接访问
        next();
    }
});

export default router;
