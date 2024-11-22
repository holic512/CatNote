<script setup lang="ts">

import {redo} from "@/views/User/Main/components/Edit/Main/Tools/Service/redo";
import '../css/editTool-button.css'
import IconRedo from "@/views/User/Main/components/Edit/Main/Tools/icon/IconRedo.vue";

const editor:any = defineModel()
</script>

<template>
  <el-tooltip
      class="box-item"
      effect="dark"
      content=" 重做 "
      :show-after="500"
      placement="bottom"
  >

    <el-button text class="button" @click="redo(editor)">
      <el-icon color="#000000" size="18">
        <IconRedo/>
      </el-icon>
    </el-button>

  </el-tooltip>
</template>

<style scoped>

</style>