<script setup lang="ts">

import {toggleOrderedList} from "../Service/toggleOrderedList.ts";
import IconOList from "../icon/IconOrderedList.vue"
import '../css/editTool-button.css'

const editor = defineModel()
</script>

<template>
  <el-tooltip
      class="box-item"
      effect="dark"
      content=" 有序排列 "
      :show-after="500"
      placement="bottom"
  >

    <!--  有序排列  -->
    <el-button text class="button" @click="toggleOrderedList(editor)">
      <el-icon color="#000000" size="18">
        <IconOList/>
      </el-icon>
    </el-button>

  </el-tooltip>
</template>

<style scoped>

</style>