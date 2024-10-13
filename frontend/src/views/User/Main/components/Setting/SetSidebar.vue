<script setup lang="ts">

</script>

<template>
  <el-text size="small" tag="b">账号</el-text>

  <el-divider style="margin: 4px 0 4px 0"/>

  <div class="sidebar-div">
    <span class="pi pi-user" style="font-size: 0.9rem;margin-left: 2px;margin-right: 4px;margin-top: 1px;"></span>
    <el-text style="color: #000;">我的账号</el-text>
  </div>

</template>

<style scoped>
.sidebar-div {
  width: 100%;
  height: 25px;
  display: flex;
  align-items: center;
  border-radius: 4px; /* 添加圆角 */
  padding: 4px 2px 4px 2px; /* 增加内边距以增强按钮的点击区域 */
  transition: background-color 0.1s ease; /* 添加过渡效果 */

  user-select: none;
}

.sidebar-div:hover {
  background-color: #EFEFED;
}
</style>