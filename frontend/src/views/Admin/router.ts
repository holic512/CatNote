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
                        name: 'admin-auth-home',
                        component: () => import('./Auth/components/login.vue'),
                    },
                    {
                        path: 'login',
                        name: 'admin-auth-login',
                        component: () => import('./Auth/components/login.vue'),
                    }
                ]
            },
            // 主内容页面
            {
                path: 'main',
                name: 'admin-main',
                component: () => import("./Main/index.vue"),
                children: [
                    {
                        path: '',
                        name: 'admin-main-home',
                        component: () => import('./Main/components/Home.vue'),
                    },
                    {
                        path: 'home',
                        name: 'admin-main-home1',
                        component: () => import('./Main/components/Home.vue'),
                    },
                    {
                        path: 'userMm',
                        name: 'admin-main-userMm',
                        component: () => import('./Main/view/userMm/UserMm.vue'),
                    },
                    {
                        path: 'noteMm',
                        name: 'admin-main-noteMm',
                        component: () => import('./Main/components/NoteMm.vue'),
                    },
                    {
                        path: 'setting',
                        name: 'admin-main-setting',
                        component: () => import('./Main/components/Setting.vue'),
                    },
                ],
            },
        ]
    }
];