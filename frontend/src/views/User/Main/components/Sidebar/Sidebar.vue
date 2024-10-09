<script setup lang="ts">

import Button from 'primevue/button';
import NoteTree from "./noteTree.vue";
import {ref} from "vue";

// 控制 noteTree 显示
const noteTreeVisible = ref(true)

</script>

<template>
  <div class="sidebar">
    <!--    头部内容  -->
    <div class="sidebar-div" style="margin-bottom: 6px">
      <div style="display: flex; justify-content: center; align-items: center; width: 38px">
        <el-avatar
            :size="24"
            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        />
      </div>
      <div style="flex: 1; /* 占据剩余空间 */">
        <div style=" height: 100%; display: flex;  align-items: center; ">
          <el-text truncated>
            Holic
          </el-text>

          <!--          <i class="pi pi-angle-down" style="font-size: 1rem;color: #708090"/>-->
        </div>
      </div>

      <div>
        <Button class="sidebar-button" text icon="pi pi-angle-double-left" size="small"/>
      </div>

      <div>
        <Button class="sidebar-button" text icon="pi pi-pen-to-square" size="small"/>
      </div>

    </div>


    <el-divider style="margin: 4px"/>
    <!--    工具栏   -->
    <div class="sidebar-div" style="margin-bottom: 1px;">
      <div class="sidebar-icon">
        <i class="pi pi-home" style="font-size: 1rem;color: #708090"/>
      </div>
      <div style="display: flex;  align-items: center;">
        <el-text>主页</el-text>
      </div>
    </div>

    <div class="sidebar-div" style="margin-bottom: 1px;">
      <div class="sidebar-icon">
        <i class="pi pi-search" style="font-size: 1rem;color: #708090"/>
      </div>
      <div style="display: flex;  align-items: center;">
        <el-text>搜索</el-text>
      </div>
    </div>


    <div class="sidebar-div" style="margin-bottom: 1px;">
      <div class="sidebar-icon">
        <i class="pi pi-calendar" style="font-size: 1rem;color: #708090"/>
      </div>
      <div style="display: flex;  align-items: center;">
        <el-text>待做</el-text>
      </div>
    </div>

    <div class="sidebar-div" style="margin-bottom: 6px;">
      <div class="sidebar-icon">
        <i class="pi pi-inbox" style="font-size: 1rem;color: #708090"/>
      </div>

      <div style="display: flex;  align-items: center;">
        <el-text>收件箱</el-text>
      </div>
    </div>
    <el-divider style="margin: 4px"/>
    <!--     笔记 控制按钮    -->
    <div class="sidebar-div" style="margin-bottom: 1px;" @click="noteTreeVisible = !noteTreeVisible">
      <div style="display: flex; justify-content: center; align-items: center; width: 50px">
        <el-text size="small">笔记</el-text>
      </div>

      <div style="flex: 1"></div>

      <div>
        <Button class="sidebar-button" text icon="pi pi-ellipsis-h" size="small" severity="secondary"/>
      </div>
      <div>
        <Button class="sidebar-button" text icon="pi pi-plus" size="small" severity="secondary"/>
      </div>
    </div>

    <!--  笔记文件树  -->
    <note-tree v-if="noteTreeVisible" style="margin-bottom: 16px;"/>


    <el-divider style="margin: 4px"/>

    <!--    工具类    -->

    <!--  账号  -->
    <div class="sidebar-div" style="margin-bottom: 1px;" @click="noteTreeVisible = !noteTreeVisible">
      <div class="sidebar-icon">
        <i class="pi pi-user" style="font-size: 1rem;color: #708090"/>
      </div>
      <div style="display: flex;  align-items: center;">
        <el-text>账号</el-text>
      </div>
    </div>

    <!--  设置  -->
    <div class="sidebar-div" style="margin-bottom: 1px;" @click="noteTreeVisible = !noteTreeVisible">
      <div class="sidebar-icon">
        <i class="pi pi-cog" style="font-size: 1rem;color: #708090"/>
      </div>
      <div style="display: flex;  align-items: center;">
        <el-text>设置</el-text>
      </div>
    </div>
  </div>

</template>

<style scoped>
.sidebar {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 8px;
}

.sidebar-div {
  width: 100%;
  height: 30px;
  display: flex;
  align-items: center;
  border-radius: 4px; /* 添加圆角 */
  padding: 4px 2px 4px 2px; /* 增加内边距以增强按钮的点击区域 */
  transition: background-color 0.1s ease; /* 添加过渡效果 */
}

/* 控制按钮图标*/
.sidebar-icon {
  display: flex;
  justify-content: right;
  align-items: center;
  width: 26px;
  margin-right: 10px;
  margin-top: 1px;
}

.sidebar-div:hover {
  background-color: #EFEFED;
}

.sidebar-button {
  width: 24px;
  height: 24px;
}

.sidebar-button:hover {
  background-color: #f8f9fa;
}


</style>