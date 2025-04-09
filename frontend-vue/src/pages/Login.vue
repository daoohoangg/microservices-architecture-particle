<script setup>
import { ref } from 'vue';
import { login } from '@/services/authService';

const email = ref('');
const password = ref('');

const handleLogin = async () => {
  try {
    const response = await login({ email: email.value, password: password.value });
    console.log(response.data);
  } catch (error) {
    console.error('Login failed', error);
  }
};
</script>

<template>
  <form @submit.prevent="handleLogin">
    <input v-model="email" placeholder="Email" />
    <input type="password" v-model="password" placeholder="Password" />
    <button type="submit" class="text-blue-200">Login</button>
  </form>
</template>
