<!--
* Description: 404页面
* Version: 1.0
-->
<script setup lang="ts">

</script>

<template>
  <div class="not-found">
    <h1>404</h1>
    <p>页面未找到</p>
    <router-link to="/">返回首页</router-link>
  </div>
</template>

<style scoped>
.not-found {
  text-align: center; /* 内容居中 */
  padding: 50px; /* 内边距 */
}
.not-found h1 {
  font-size: 100px; /* 设置大字体大小 */
  margin: 0; /* 去除默认的外边距 */
}
.not-found p {
  font-size: 24px; /* 设置文本字体大小 */
}
.not-found a {
  color: #03bb3e; /* 设置链接颜色 */
  text-decoration: none; /* 去除链接下划线 */
}
.not-found a:hover {
  text-decoration: underline; /* 悬停时添加下划线 */
}
</style>
