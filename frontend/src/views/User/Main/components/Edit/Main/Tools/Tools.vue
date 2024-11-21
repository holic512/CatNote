<!--  用于 文本编辑器上方的 工具栏 -->
<script setup lang="ts">
import BoldText from "./components/BoldText.vue";
import ItalicText from "./components/ItalicText.vue";
import Underline from "./components/Underline.vue";
import Strikethrough from "./components/Strikethrough.vue";
import UnorderedList from "./components/UnorderedList.vue";
import OrderedList from "./components/OrderedList.vue";
import PlusMore from "./Plus/PlusMore.vue";
import Undo from "./components/Undo.vue";
import Redo from "./components/Redo.vue";
import HighlightText from "./components/TextColor.vue";

// 获取编辑器实例
const editor = defineModel()

</script>

<template>
  <div class="container">

    <!--  撤销  -->
    <Undo v-model="editor"/>

    <!--  重做  -->
    <Redo v-model="editor"/>


    <el-divider direction="vertical"/>

    <!--   插入   -->
    <PlusMore v-model="editor"/>


    <el-divider direction="vertical"/>

    <!--    加粗文本    -->
    <BoldText v-model="editor"/>

    <!-- 斜体 -->
    <ItalicText v-model="editor"/>

    <!-- 下划线 -->
    <Underline v-model="editor"/>

    <!--删除线-->
    <Strikethrough v-model="editor"/>

    <el-divider direction="vertical"/>

    <!--高亮-->
    <HighlightText v-model="editor"/>

    <el-divider direction="vertical"/>

    <!--  无序排列  -->
    <UnorderedList v-model="editor"/>

    <!--  有序排列  -->
    <OrderedList v-model="editor"/>


  </div>

</template>

<style scoped>
.container {
  width: 100%;
  height: 44px;
  border-bottom: 1px solid #F0F3F5;

  display: flex;
  align-items: center;

  padding-left: 12px;
}
</style>