// homeRoutes.js

import HomeView from './index.vue'
import LoginPage from '../LoginPage/LoginPage.vue'

export default [
    {
        path: '/',
        name: 'HomeView',
        component: HomeView
    },
    {
        path: '/:pathMatch(.*)*',
        name: '404',
        component: () => import('./404.vue'),
    },
    {
        path: '/login',
        name: 'Login',
        component: LoginPage,
    },

];
