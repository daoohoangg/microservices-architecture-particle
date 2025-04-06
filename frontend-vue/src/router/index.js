import { createRouter, createWebHistory } from 'vue-router';
import Login from '@/pages/Login.vue';
import Dashboard from '@/pages/Dashboard.vue';
import AuthLayout from '@/layouts/AuthLayout.vue'

const routes = [
  { path: '/login', component: Login },
  { path: '/dashboard', component: Dashboard },
  { path: '/auth', component: AuthLayout}
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
