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
import EmojiPicker from "@/views/User/Main/components/Edit/Main/Tools/components/EmojiPicker.vue";
import {useUserPreferencesStore} from "@/views/User/Main/Pinia/userPreferencesStore";
import InsertTable from "@/views/User/Main/components/Edit/Main/Tools/components/InsertTable.vue";

// 获取编辑器实例
const editor = defineModel()

// 获取个性化实例
const PreferencesStore = useUserPreferencesStore()

</script>

<template>
  <div class="ToolsContainer">

    <!--  撤销  -->
    <Undo v-model="editor"/>

    <!--  重做  -->
    <Redo v-model="editor"/>


    <el-divider direction="vertical"/>

    <!--   插入   -->
    <PlusMore v-model="editor"/>

    <!--  表格  -->
    <InsertTable v-model="editor"/>


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

    <!--  表情选择器  -->
    <EmojiPicker v-model="editor"/>


    <div style="flex: 1"/>

    <el-button text class="button" ref="buttonRef" style="margin-right: 8px"
               @click="PreferencesStore.toggleEditorToolbar()">
      <el-icon>
        <BottomLeft/>
      </el-icon>
    </el-button>

  </div>

</template>

<style scoped>
.ToolsContainer {
  width: 100%;
  height: 36px;
  border-bottom: 1px solid #F0F3F5;

  display: flex;
  align-items: center;

  padding-left: 12px;
}
</style>