// AdminRouter


export default [
    {
        path: '/Admin',
        component: () => import("./index.vue"),
        meta: {requiresAuth: true},
        children: [
            // 默认页面
            {
                path: '',
                name: 'admin-home',
                meta: {requiresAuth: false},
                component: () => import('./Auth/components/login.vue'),
            },
            // 授权页面
            {
                path: 'auth',
                name: 'admin-auth',
                meta: {requiresAuth: false},
                component: () => import('./Auth/index.vue'),
                children: [
                    {
                        path: '',
                        name: 'user-auth-home',
                        component: () => import('./Auth/components/login.vue'),
                    },
                    {
                        path: 'login',
                        name: 'user-auth-login',
                        component: () => import('./Auth/components/login.vue'),
                    }
                ]
            },
            // 主内容页面
            {
                path: 'main',
                name: 'admin-main',
                component: () => import("./Main/index.vue"),
                children: [],
            },
        ]
    }
];