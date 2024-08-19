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
            alias: '/inicio',
        },
        {
            path: '/publish',
            name: 'publish',
            component: () => import('../views/publishView.vue'),
            alias: '/inicio',
        },
        {
            path: '/account',
            name: 'account',
            component: () => import('../views/accountView.vue'),
            alias: '/inicio',
        },
        {
            path: '/tier',
            name: 'tier',
            component: () => import('../views/tierView.vue'),
            alias: '/inicio',
        },
    ],
});

export default router;