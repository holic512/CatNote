export default [
    {
        path: '/user',
        component: () => import('./index.vue'),
        meta: { requiresAuth: true },
        children: [
            // 默认页面
            {
                path: '',
                name: 'user-home',
                meta: { requiresAuth: false },
                component: () => import('./Auth/components/login.vue'),
            },
            // 授权页面
            {
                path: 'auth',
                name: 'user-auth',
                meta: { requiresAuth: false },
                component: () => import("./Auth/index.vue"),
                children: [
                    {
                        path: '',
                        name: 'user-auth-home',
                        component: () => import('./Auth/components/login.vue'),
                    },
                    {
                        path: 'login',
                        name: 'user-login',
                        component: () => import('./Auth/components/login.vue'),
                    },
                    {
                        path: 'register',
                        name: 'user-register',
                        component: () => import('./Auth/components/register.vue'),
                    },
                    {
                        path: 'forgetPassword',
                        name: 'user-forgetPassword',
                        component: () => import('./Auth/components/forgetPassword.vue'),
                    }
                ],
            },
            // 主页面
            {
                path: 'main',
                name: 'user-main',
                component: () => import("./Main/index.vue"),
                children: [],
            },
        ]
    }
];
