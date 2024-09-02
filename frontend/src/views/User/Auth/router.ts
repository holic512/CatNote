// Auth/router.ts

export default [
    {
        path: 'login',
        name: 'user-login',
        component: () => import('./components/login.vue'),  // 动态加载 Login 组件
    },
    {
        path: 'register',
        name: 'user-register',
        component: () => import('./components/register.vue'),  // 动态加载 Register 组件
    },
    {
        path: 'forgetPassword',
        name: 'user-forgetPassword',
        component: () => import('./components/forgetPassword.vue'),
    }
];
