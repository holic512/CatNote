<script setup lang="ts">
import {isItalicActive, italic} from "@/views/User/Main/components/Edit/Main/Tools/Service/Italic.ts";

import '../css/editTool-button.css'
import IconItalic from "@/views/User/Main/components/Edit/Main/Tools/icon/IconItalic.vue";
const editor = defineModel()

</script>

<template>
  <el-tooltip
      class="box-item"
      effect="dark"
      content=" 斜体 "
      :show-after="500"
      placement="bottom"
  >

    <!-- 斜体 -->
    <el-button text class="button" @click="italic(editor)" style="margin: 0 !important">
      <el-icon color="#000000" size="16" v-show="!isItalicActive(editor)">
        <IconItalic/>
      </el-icon>
      <el-icon color="#2383E2" size="16" v-show="isItalicActive(editor)">
        <IconItalic/>
      </el-icon>
    </el-button>


  </el-tooltip>
</template>

<style scoped>

</style>