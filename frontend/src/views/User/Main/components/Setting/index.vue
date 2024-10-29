<script setup lang="ts">
import SetSidebar from "@/views/User/Main/components/Setting/SetSidebar.vue";
import UserInfo from "@/views/User/Main/components/Setting/UserInfo.vue";

const addUserVisible = defineModel()
</script>

<template>
  <el-dialog v-model="addUserVisible" class="dialog">
    <template #header="{ close, titleId, titleClass }">

    </template>

    <el-container>
      <el-aside width="140px" style="padding:0 8px 0 0;">
        <SetSidebar/>
      </el-aside>
      <el-main style="padding:0;">
        <!--  滚动条  -->
        <el-scrollbar height="65vh" style="padding: 0 12px 0 0">


          <UserInfo/>


        </el-scrollbar>
      </el-main>
    </el-container>
  </el-dialog>
</template>

<style scoped>
.dialog {
  min-width: 400px;
  max-width: 850px;
  width: 65vw;

  max-height: 650px;
  height: calc(70vh + 16px);
  padding: 0;
  background-color: #F7F7F5;
}
</style>