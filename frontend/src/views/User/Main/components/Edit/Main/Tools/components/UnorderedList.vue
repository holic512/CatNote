<script setup lang="ts">
import '../css/editTool-button.css'


import {toggleBulletList} from "@/views/User/Main/components/Edit/Main/Tools/Service/toggleBulletList";
import IconUList from "@/views/User/Main/components/Edit/Main/Tools/icon/IconUnorderedList.vue";

const editor: any = defineModel()


</script>

<template>
  <el-tooltip
      class="box-item"
      effect="dark"
      content=" 无序排列 "
      :show-after="500"
      placement="bottom"
  >

    <!--  无序排列  -->
    <el-button text class="tool-button" @click="toggleBulletList(editor)">
      <el-icon color="#000000" size="18">
        <IconUList/>
      </el-icon>
    </el-button>

  </el-tooltip>
</template>

<style scoped>

</style>