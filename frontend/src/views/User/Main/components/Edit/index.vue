<script setup>

import TipTap from "@/views/User/Main/components/Edit/Main/TipTap.vue";
import TopTools from "@/views/User/Main/components/Edit/Top/TopTools.vue";
import {onBeforeUnmount, onMounted, ref} from "vue";

const mainHeight = ref(window.innerHeight - 120);

// 窗口大小变化时重新计算面板宽度
const onWindowResize = () => {
  mainHeight.value = window.innerWidth - 48 - 32; // 减去分割线宽度
};

// 绑定和移除事件监听
onMounted(() => {
  window.addEventListener('resize', onWindowResize);
});

onBeforeUnmount(() => {
  window.removeEventListener('resize', onWindowResize);
});

</script>
<template>
  <div class="common-layout">
    <el-container style="height: 100vh">
      <!--  工具栏  -->
      <el-header class="common-header">
        <TopTools/>
      </el-header>
      <!--  编辑器  -->
      <el-main :style="{height: mainHeight + 'px' }" style="padding: 0">
        <TipTap/>
      </el-main>
      <el-footer style="height: 32px;">
        尾部
      </el-footer>
    </el-container>
  </div>


</template>


<style scoped>
.common-header{
  border-bottom: 1px solid #F0F3F5;
  height: 48px;
}
</style>
