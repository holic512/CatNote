<script setup lang="ts">

import {undo} from "@/views/User/Main/components/Edit/Main/Tools/Service/undo";
import iconUndo from "@/views/User/Main/components/Edit/Main/Tools/icon/IconUndo.vue";

import '../css/editTool-button.css'

const editor:any = defineModel()
</script>

<template>
  <el-tooltip
      class="box-item"
      effect="dark"
      content=" 撤销 "
      :show-after="500"
      placement="bottom"
  >

    <el-button text class="button" @click="undo(editor)">
      <el-icon color="#000000" size="18">
        <iconUndo/>
      </el-icon>
    </el-button>

  </el-tooltip>
</template>

<style scoped>

</style>