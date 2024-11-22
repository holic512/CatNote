<script setup lang="ts">

import {ContextMenu, ContextMenuGroup, ContextMenuItem, ContextMenuSeparator} from "@imengyu/vue3-context-menu";
import {onRightNFAddNote} from "@/views/User/Main/components/Sidebar/RightMenu/Service/onRightNFAddNote";
import {onRightNFAddFolder} from "@/views/User/Main/components/Sidebar/RightMenu/Service/onRightNFAddFolder";
import {useRenameData} from "@/views/User/Main/components/Sidebar/Pinia/RenameData";


const NodeMenuOption: any = defineModel();

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
    <context-menu-group label="导出">
      <context-menu-item label="World"/>
      <context-menu-item label="Pdf"/>
    </context-menu-group>

    <context-menu-separator/>

    <context-menu-item label="属性"/>

    <context-menu-group label="更多">
      <context-menu-item label="置顶"/>
      <context-menu-item label="分享"/>
      <context-menu-item label="收藏"/>
      <context-menu-item label="阅读密码"/>
      <context-menu-item label="查看历史版本"/>
    </context-menu-group>

  </context-menu>
</template>

<style scoped>

</style>