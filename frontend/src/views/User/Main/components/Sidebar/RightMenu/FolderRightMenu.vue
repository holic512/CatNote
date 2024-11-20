<script setup lang="ts">

import {ContextMenu, ContextMenuGroup, ContextMenuItem, ContextMenuSeparator} from "@imengyu/vue3-context-menu";
import {
  RightMenuOptionInterface
} from "@/views/User/Main/components/Sidebar/RightMenu/interface/RightMenuOptionInterface.js";
import {onRightNFAddNote} from "@/views/User/Main/components/Sidebar/RightMenu/Service/onRightNFAddNote.ts";
import {onRightNFAddFolder} from "@/views/User/Main/components/Sidebar/RightMenu/Service/onRightNFAddFolder.ts";
import {useRenameData} from "@/views/User/Main/components/Sidebar/Pinia/RenameData.ts";


const NodeMenuOption: RightMenuOptionInterface = defineModel();

const IsRename = useRenameData()

</script>

<template>
  <context-menu
      v-model:show="NodeMenuOption.show"
      :options="NodeMenuOption.optionsComponent"
  >
    <context-menu-group label="新建">
      <context-menu-item label="空白文档" @click="onRightNFAddNote()"/>
      <context-menu-item label="文件夹" @click="onRightNFAddFolder()"/>
    </context-menu-group>

    <context-menu-separator/>

    <context-menu-item label="删除"/>

    <context-menu-separator/>

    <context-menu-item label="重命名" @click="IsRename.IsRename()"/>
    <context-menu-item label="移动到"/>
    <context-menu-item label="复制"/>

    <context-menu-separator/>

    <context-menu-item label="批量导出" disabled/>

    <context-menu-separator/>
    <context-menu-item label="属性"/>
    <context-menu-group label="更多">
      <context-menu-item label="分享"/>
      <context-menu-item label="收藏"/>
      <context-menu-item label="阅读密码"/>
    </context-menu-group>

  </context-menu>
</template>

<style scoped>

</style>