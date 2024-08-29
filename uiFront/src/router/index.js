import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({

    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: () => import('../views/anonMain.vue'),
            alias: '/inicio',
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('../views/loginView.vue'),
            alias: '/login',
        },
        {
            path: '/publish',
            name: 'publish',
            component: () => import('../views/publishView.vue'),
            alias: '/publish',
        },
        {
            path: '/account',
            name: 'account',
            component: () => import('../views/accountView.vue'),
            alias: '/account',
        },
        {
            path: '/top',
            name: 'top',
            component: () => import('../views/topView.vue'),
            alias: '/top',
        },
    ],
});

export default router;