export default [
    {
        path: '/user',
        component: () => import('./index.vue'),
        meta: {requiresAuth: true},
        children: [
            // 默认页面 - 授权页面
            {
                path: '',
                name: 'user-home',
                meta: {requiresAuth: false},
                component: () => import('./Auth/login/login.vue'),
            },
            // 授权页面
            {
                path: 'auth',
                name: 'user-auth',
                meta: {requiresAuth: false},
                component: () => import("./Auth/index.vue"),
                children: [
                    {
                        path: '',
                        name: 'user-auth-home',
                        component: () => import('./Auth/login/login.vue'),
                    },
                    {
                        path: 'login',
                        name: 'user-login',
                        component: () => import('./Auth/login/login.vue'),
                    },
                    {
                        path: 'register',
                        name: 'user-register',
                        component: () => import('./Auth/register/register.vue'),
                    },
                    {
                        path: 'forgetPassword',
                        name: 'user-forgetPassword',
                        component: () => import('./Auth/forgetPw/forgetPassword.vue'),
                    }
                ],
            },
            // 主页面
            {
                path: 'main',
                name: 'user-main',
                meta: {requiresAuth: true},
                component: () => import("./Main/index.vue"),
                children: [
                    {
                        path: '',
                        name: 'user-main-home',
                        component: () => import('./Main/components/Home/index.vue'),
                    },
                    {
                        path: 'home',
                        name: 'user-main-home1',
                        component: () => import('./Main/components/Home/index.vue'),
                    },
                    {
                        path: 'edit',
                        name: 'user-edit',
                        component: () => import('./Main/components/Edit/index.vue'),
                    },
                    {
                        path: 'todoList',
                        name: 'user-todoList',
                        component: () => import('./Main/components/TodoList/TodoList.vue'),
                    },
                ],
            },
        ]
    }
];
