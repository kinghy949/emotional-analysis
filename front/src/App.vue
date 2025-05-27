<script setup lang="ts">
// Ant Design components
import 'ant-design-vue/lib/style/reset.css';
</script>

<template>
  <a-layout style="min-height: 100vh;">
    <a-layout-header style="background: #fff; box-shadow: 0 2px 8px #f0f1f2;">
      <a-typography-title level="3" style="margin: 0; color: #1677ff;">情感倾向分析系统</a-typography-title>
    </a-layout-header>
    <a-layout-content style="padding: 24px; background: #f5f5f5;">
      <RouterView />
    </a-layout-content>
  </a-layout>
</template>

<style scoped>
.a-layout-header {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 64px;
  padding: 0 24px;
}
.a-layout-content {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  min-height: 80vh;
}
</style>
