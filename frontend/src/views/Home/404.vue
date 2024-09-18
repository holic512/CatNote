<!--
* Description: 404 页面 - 简约黑白风格
* Version: 1.0
-->
<script setup lang="ts">

</script>

<template>
  <div class="not-found">
    <h1>404</h1>
    <p>Oops! 页面不存在</p>
    <router-link to="/">返回首页</router-link>
  </div>
</template>

<style scoped>
.not-found {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh; /* 全屏高度 */
  background-color: #f8f9fa; /* 淡灰色背景 */
  color: #333; /* 深灰色字体 */
  font-family: 'Helvetica Neue', Arial, sans-serif; /* 现代无衬线字体 */
}

.not-found h1 {
  font-size: 120px; /* 特大字体 */
  font-weight: 700; /* 加粗 */
  margin: 0;
  color: #000; /* 纯黑色 */
}

.not-found p {
  font-size: 18px; /* 稍小的字体 */
  color: #666; /* 浅灰色 */
  margin-top: 10px;
}

.not-found a {
  margin-top: 20px;
  padding: 10px 20px;
  border: 2px solid #333; /* 黑色边框 */
  border-radius: 5px;
  color: #333;
  text-decoration: none;
  font-weight: 500;
  transition: background-color 0.3s, color 0.3s; /* 添加过渡效果 */
}

.not-found a:hover {
  background-color: #333; /* 黑色背景 */
  color: #fff; /* 白色文字 */
}
</style>
