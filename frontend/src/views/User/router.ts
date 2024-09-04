export default [
    {
        path: '/user',
        component: () => import('./index.vue'), // 假设你有一个用户布局组件
        children: [
            {
                path: '',
                name: 'user-home',
                component: () => import('./Auth/components/login.vue'),
            },
            {
                path: 'auth',
                name: 'user-auth',
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
            {
                path: 'main',
                name: 'user-main',
                component: () => import("./Main/index.vue"),
                children: [],
            },
        ]
    }
];
