<script setup lang="ts">
import type Node from 'element-plus/es/components/tree/src/model/node'
import {getNoteTree} from "@/views/User/Main/components/Sidebar/service/getNoteTree.ts";

interface Tree {
  id: number;
  label: string;
  leaf?: boolean;
}

const props = {
  label: 'label',
  children: 'zones',
  isLeaf: 'leaf',
}

const loadNode = async (node: Node, resolve: (data: Tree[]) => void) => {
  // 根目录 笔记目录加载
  if (node.level === 0) {
    const data = await getNoteTree(null)
    return resolve(data)
  }

  // 非根目录 笔记目录加载
  if (node.level > 1) return resolve([])
  const data = await getNoteTree(node.data.id)
  resolve(data)


}

</script>

<template>
  <el-tree
      class="el-tree"
      draggable
      lazy
      node-key="id"
      :props="props"
      :load="loadNode"
  >
    <template #default="{node,data }">
      <div v-if="data.leaf == true" style="padding: 0">
        <el-icon size="12">
          <Notebook/>
        </el-icon>
      </div>
      <div v-else>
        <el-icon size="12">
          <Folder/>

        </el-icon>
      </div>
      <el-text style="margin-left: 6px;">
        {{ node.label }}
      </el-text>


    </template>
  </el-tree>

</template>

<style scoped>
.el-tree {
  width: 100%;
  padding: 2px;
  background-color: #f7f7f5;
}

:deep(.el-tree-node__content) {
  height: 28px;
}

:deep(.el-tree-node__content):hover {
  background-color: #EFEFED;
}

:deep(.el-tree-node__content):focus {
  background-color: #EFEFED;
}


/* 选中状态时的背景色 */
:deep(.el-tree-node.is-current > .el-tree-node__content) {
  background-color: #EFEFED;
  color: #333; /* 选中时的文本颜色 */
}

/* 叶子节点的选中样式 */
:deep(.el-tree-node.is-leaf.is-current > .el-tree-node__content) {
  background-color: #EFEFED;
  color: #333; /* 叶子节点选中时的文本颜色 */
}

:deep(.el-tree-node.is-expanded > .el-tree-node__content) {
  background-color: #EFEFED !important; /* 保持相同的自定义背景色 */
}

</style>