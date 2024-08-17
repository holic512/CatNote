// router.ts

import { createRouter, createWebHistory } from 'vue-router';
import homeRoutes from '../views/Home/router.ts'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        ...homeRoutes,

    ]
});

export default router;
