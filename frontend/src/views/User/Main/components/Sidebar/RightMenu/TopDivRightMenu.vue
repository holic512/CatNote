<script setup lang="ts">

import {ContextMenu, ContextMenuGroup, ContextMenuItem} from "@imengyu/vue3-context-menu";
import {AddNote} from "@/views/User/Main/components/Sidebar/RightMenu/Service/AddNote";
import {AddFolder} from "@/views/User/Main/components/Sidebar/RightMenu/Service/AddFolder";

// 获取 NodeTree 页面传递来的 数据
const TopDivMenuOption: any = defineModel();

</script>

<template>
  <context-menu
      v-model:show="TopDivMenuOption.show"
      :options="TopDivMenuOption.optionsComponent"
  >
    <context-menu-group label="新建">
      <context-menu-item label="空白文档" @click="AddNote(0)"/>
      <context-menu-item label="文件夹" @click="AddFolder(0)"/>
    </context-menu-group>

  </context-menu>
</template>

<style scoped>

</style>