<script setup lang="ts">
import {useRouter} from 'vue-router';

// 路由实例
const router = useRouter();

const goHome = () => {
  router.push('/');
};
</script>

<template>
  <div class="error-container">
    <el-card class="error-box">
      <h1 class="errorTitle">权限错误</h1>
      <p class="description">对不起，您没有权限访问此页面。</p>
      <el-button type="primary" class="home-button" @click="goHome">返回首页</el-button>
    </el-card>
  </div>
</template>

<style scoped>
.error-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
}

.error-box {
  width: 400px;
  padding: 40px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.errorTitle {
  margin-bottom: 24px;
  color: #d9534f;
  font-size: 30px;
  font-weight: 800;
}

.description {
  margin-bottom: 20px;
  color: #666;
  font-size: 16px;
}

.home-button {
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 4px;
  background-color: #333;
  color: #fff;
  font-size: 16px;
  cursor: pointer;
  margin-top: 20px;
}

.home-button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
</style>
