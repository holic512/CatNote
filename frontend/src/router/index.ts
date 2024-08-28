// router.ts

import { createRouter, createWebHistory } from 'vue-router';
import homeRoutes from '../../../../项目/CatNote-master (1)/CatNote-master/frontend/src/views/Home/router.ts'
import adminRoutes from '../views/Admin/router.ts'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        ...homeRoutes,
        ...adminRoutes

    ]
});

export default router;
