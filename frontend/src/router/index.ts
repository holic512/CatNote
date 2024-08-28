// router.ts

import { createRouter, createWebHistory } from 'vue-router';
import homeRoutes from '../views/Home/router.ts'
import adminRoutes from '../views/Admin/router.ts'
import userRouters from '../views/User/router.ts'


const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        ...homeRoutes,
        ...userRouters,
        ...adminRoutes,

    ]
});

export default router;
