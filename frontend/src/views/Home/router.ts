// homeRoutes.js

import HomeView from './index.vue'

export default [
    {
        path: '/',
        name: 'HomeView',
        component: HomeView
    },
    {
        path: '/pd',
        component: () => import('./PermissionDenied.vue'),
    },
    {
        path: '/:pathMatch(.*)*',
        name: '404',
        component: () => import('./404.vue'),
    },
];
