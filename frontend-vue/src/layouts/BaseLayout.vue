// src/components/BaseLayout.vue
<template>
  <div>
    <header><slot name="header"></slot></header>
    <main><slot></slot></main>
    <footer><slot name="footer"></slot></footer>
  </div>
</template>

<!-- // Sử dụng trong các page:
<BaseLayout>
  <template #header>
    <h1>Welcome to the app</h1>
  </template>

  <p>Main content here</p>

  <template #footer>
    <p>Footer content here</p>
  </template>
</BaseLayout> -->
