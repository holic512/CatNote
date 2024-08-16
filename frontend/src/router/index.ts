// router.ts

import { createRouter, createWebHistory } from 'vue-router';
import homeRoutes from '../view/Home/router.ts'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        ...homeRoutes,
        // {
        //     path: '/:pathMatch(.*)*',
        //     name: '404',
        //     component: () => import('../views/404.vue'),
        //
        // },
    ]
});

export default router;
