<script setup lang="ts">
import Bold from "@/views/User/Main/components/Edit/Main/Tools/icon/IconBold.vue";
import {bold, isBoldActive} from "@/views/User/Main/components/Edit/Main/Tools/Service/bold";
import '../css/editTool-button.css'

const editor:any = defineModel()

</script>

<template>
  <el-tooltip
      class="box-item"
      effect="dark"
      content=" 加粗 "
      :show-after="500"
      placement="bottom"
  >

    <!-- 加粗 -->
    <el-button text class="button" @click="bold(editor)">
      <el-icon color="#000000" size="18" v-show="!isBoldActive(editor)">
        <Bold/>
      </el-icon>
      <el-icon color="#2383E2" size="18" v-show="isBoldActive(editor)">
        <Bold/>
      </el-icon>
    </el-button>


  </el-tooltip>
</template>

<style scoped>

</style>