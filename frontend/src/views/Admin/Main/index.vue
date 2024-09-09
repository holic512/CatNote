<script setup lang="ts">
import Button from 'primevue/button';
import {ref} from "vue";

const button = ref(1)

import UserMm from "./components/UserMm.vue";

</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="90px" class="aside-layout">
        <div class="aside-layout-div">
          <!--          &lt;!&ndash; 图像 &ndash;&gt;-->
          <!--          <div class="aside-item">-->
          <!--            <img src="../assets/images/logo.png" class="logo"/>-->
          <!--          </div>-->

          <!-- 按钮组 -->
          <div class="button-group">
            <div
                v-tooltip="{ value: '主页', showDelay: 1000, hideDelay: 300 }">
              <Button icon="pi pi-home" v-if="button == 1"/>
              <Button icon="pi pi-home" text v-else @click="button = 1"/>
            </div>

            <div
                v-tooltip="{ value: '用户管理', showDelay: 1000, hideDelay: 300 }">
              <Button icon="pi pi-user" v-if="button == 2"/>
              <Button icon="pi pi-user" text v-else @click="button = 2"/>
            </div>

            <div
                v-tooltip="{ value: '笔记管理', showDelay: 1000, hideDelay: 300 }">
              <Button icon="pi pi-book" v-if="button == 3"/>
              <Button icon="pi pi-book" text v-else @click="button = 3"/>
            </div>


          </div>

          <!-- 单独按钮 -->
          <div class="aside-item">
            <div
                v-tooltip="{ value: '设置', showDelay: 1000, hideDelay: 300 }">
              <Button icon="pi pi-cog" v-if="button == 5"/>
              <Button icon="pi pi-cog" text v-else @click="button = 5"/>
            </div>

          </div>
        </div>
      </el-aside>


      <el-main class="main-layout">
        <div class="main-content">

          <UserMm></UserMm>


        </div>
      </el-main>

    </el-container>
  </div>
</template>

<style>
.aside-layout {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px 10px 20px 20px;

}

.aside-layout-div {
  height: 94.5%;
  width: 100%; /* 根据需要调整宽度 */

  background-color: #F8FAFC;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-shadow: 0 0 0 1px rgba(0, 0, 0, 0.1);
}

.aside-item {
  margin: 10px 0; /* 设置上下间距 */
}

.logo {
  width: 32px;
  height: 32px;
}

.button-group {
  display: flex;
  flex-direction: column;
  gap: 10px; /* 按钮间距 */
}

.button-group button {
  transition: transform 0.2s ease; /* 按钮的点击缩放动效 */
}

.button-group button:active {
  transform: scale(0.9); /* 点击时缩小效果 */
}

.main-layout {
  height: 100vh;
  padding-left: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.main-content {

  height: 94.5%;
  width: 100%;
  box-shadow: 0 0 0 1px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  padding: 15px; /* 内边距 */
}

</style>
