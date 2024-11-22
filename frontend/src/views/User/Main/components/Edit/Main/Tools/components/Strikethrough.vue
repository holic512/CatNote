<script setup lang="ts">

import {isStrikeActive, strike} from "@/views/User/Main/components/Edit/Main/Tools/Service/strike";
import '../css/editTool-button.css'
import IconStrikethrough from "@/views/User/Main/components/Edit/Main/Tools/icon/IconStrikethrough.vue";

const editor:any = defineModel()

</script>

<template>
  <el-tooltip
      class="box-item"
      effect="dark"
      content=" 删除线 "
      :show-after="500"
      placement="bottom"
  >

    <!--删除线-->
    <el-button text class="button" @click="strike(editor)">
      <el-icon color="#000000" size="18" v-show="!isStrikeActive(editor)">
        <IconStrikethrough/>
      </el-icon>

      <el-icon color="#2383E2" size="18" v-show="isStrikeActive(editor)">
        <IconStrikethrough/>
      </el-icon>

    </el-button>

  </el-tooltip>
</template>

<style scoped>

</style>