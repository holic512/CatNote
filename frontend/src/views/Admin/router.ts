// AdminRouter


export default [
    {
        path: '/Admin',
        component: () => import("./index.vue"),
        children: [
            {
                path: '',
                name: 'admin-home',
                component: () => import('./Auth/components/login.vue'),
            },
            {
                path: 'auth',
                name: 'admin-auth',
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
            {
                path: 'main',
                name: 'admin-main',
                component: () => import("./Main/index.vue"),
                children: [],
            },
        ]
    }
];