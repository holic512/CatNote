<script setup lang="ts">

import AddClass from "@/views/User/Main/components/TodoList/TodoListTree/addClass/addClass.vue";
import {ref} from "vue";
import {useTodoState} from "@/views/User/Main/components/TodoList/Pinia/TodoState";

const todoClasses = [
  {
    id: 1,
    label: '自定义分类1',
  },
  {
    id: 2,
    label: 'Todo List',
  },
  {
    id: 3,
    label: 'Todo List',
  },
]


// 控制是否显示 添加分类的 Dialog
const AddClassDialogVisible = ref(false);

// 初始化 页面内容切换 state
const todoState = useTodoState();

</script>

<template>
  <div>
    <!--    分类大标题   -->
    <div style="display: flex;">
      <el-text>分类</el-text>
      <div style="flex: 1;"/>

      <!--      新建 分类按钮   -->
      <el-button text style="width: 30px;height: 30px;" @click="AddClassDialogVisible = true">
        <el-icon>
          <Plus/>
        </el-icon>
      </el-button>
    </div>

    <!--    全部 和 未分类   -->
    <div class="class-tree-button" @click="todoState.ToAll()">
      <el-text class="class-tree-button-text">
        <el-icon size="18">
          <Menu/>
        </el-icon>
        全部待办
      </el-text>
    </div>

    <div class="class-tree-button">
      <el-text class="class-tree-button-text">
        <el-icon size="18">
          <Grid/>
        </el-icon>
        未分类
      </el-text>
    </div>

    <!--    自定义分类   -->
    <div v-for="todoClass in todoClasses" class="class-tree-button">
      <el-text class="class-tree-button-text">
        <el-icon size="18">
          <Calendar/>
        </el-icon>
        {{ todoClass.label }}
      </el-text>
    </div>

    <!--    回收站   -->
    <div class="class-tree-button">
      <el-text class="class-tree-button-text">
        <el-icon size="18">
          <DeleteFilled/>
        </el-icon>
        回收站
      </el-text>
    </div>


    <!--    添加分类结构   -->
    <AddClass v-model="AddClassDialogVisible"/>
  </div>
</template>

<style scoped>
.class-tree-button {
  display: flex;
  align-items: center;
  padding: 8px;
  border-radius: 4px;
  margin: 2px;

  user-select: none;
}

.class-tree-button:hover {
  background-color: #F0F2F5;
}

.class-tree-button-text {
  display: flex;
  gap: 6px;
}

</style>