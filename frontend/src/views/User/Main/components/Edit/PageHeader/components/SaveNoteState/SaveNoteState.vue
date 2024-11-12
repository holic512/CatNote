<script setup lang="ts">

import {useSaveNoteState} from "@/views/User/Main/components/Edit/Pinia/SaveNoteState.ts";
// 创建 存储 笔记保存状态 的Pinia
const editorState = useSaveNoteState()
</script>

<template>
  <el-text size="small" type="info" class="el-text" v-show="editorState.isSaved">
    <el-icon>
      <SuccessFilled/>
    </el-icon>
    已保存
  </el-text>
  <el-text size="small" type="warning" class="el-text" v-show="!editorState.isSaved">
    <el-icon>
      <WarningFilled/>
    </el-icon>
    未保存
  </el-text>
</template>

<style scoped>
.el-text {
  margin-left: 8px;
}
</style>