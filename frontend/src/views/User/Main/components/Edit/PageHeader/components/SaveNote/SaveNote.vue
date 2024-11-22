<!--  页标签 中的 保存笔记按钮  -->
<script setup lang="ts">

import IconSaveNote from "./IconSaveNote.vue";
import {useSaveNoteState} from "@/views/User/Main/components/Edit/Pinia/SaveNoteState";
import {SaveNote} from "@/views/User/Main/components/Edit/PageHeader/components/SaveNote/SaveNote";

// 创建 存储 笔记保存状态 的Pinia
const editorState = useSaveNoteState()

// 获取父组件的 editor
const editor:any = defineModel()

</script>

<template>
  <el-tooltip
      v-if="!editorState.isSaved"
      class="box-item"
      effect="dark"
      content=" 保存 "
      :show-after="500"
      placement="bottom"
  >

    <!--    <el-button text class="button" @click="editorState.saveContent()">-->
    <el-button text class="button" @click="SaveNote(editor)">
      <el-icon color="#E6A23C" size="18">
        <IconSaveNote/>
      </el-icon>
    </el-button>

  </el-tooltip>
</template>

<style scoped>
.button {
  width: 30px;
  height: 30px;
}
</style>