<script setup lang="ts">

import Dialog from "primevue/dialog";

// 控制添加用户页面
const onlineUserVisible = defineModel()
</script>

<template>
  <Dialog v-model:visible="onlineUserVisible" :draggable="false" modal header="在线用户页面" :style="{ width: '600px'}"
          :pt="{
    header: { style: { paddingBottom:'10px',paddingTop:'10px'} },
    content: { style: { borderTop: '1px solid #E2E8F0'} },
  }">


  </Dialog>
</template>

<style scoped>

</style>