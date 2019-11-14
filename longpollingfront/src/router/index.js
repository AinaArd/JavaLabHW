import Vue from 'vue'
import Router from 'vue-router'
import Registration from '@/components/Registration'
import Login from '@/components/Login'
import Chat from '@/components/Chat'

Vue.use(Router);

const router = new Router({
    mode: 'history',
    routes: [
        {
            path: '/chat',
            name: 'chat',
            component: Chat
        },
        {
            path: '/login',
            name: 'login',
            component: Login
        },
        {
            path: '/signUp',
            name: 'signUp',
            component: Registration
        },
    ]
});

export default router
