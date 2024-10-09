<script setup lang="ts">
import type Node from 'element-plus/es/components/tree/src/model/node'
import type {DragEvents} from 'element-plus/es/components/tree/src/model/useDragNode'
import type {
  AllowDropType,
  NodeDropType,
} from 'element-plus/es/components/tree/src/tree.type'

const handleDragStart = (node: Node, ev: DragEvents) => {
  console.log('drag start', node)
}
const handleDragEnter = (
    draggingNode: Node,
    dropNode: Node,
    ev: DragEvents
) => {
  console.log('tree drag enter:', dropNode.label)
}
const handleDragLeave = (
    draggingNode: Node,
    dropNode: Node,
    ev: DragEvents
) => {
  console.log('tree drag leave:', dropNode.label)
}
const handleDragOver = (draggingNode: Node, dropNode: Node, ev: DragEvents) => {
  console.log('tree drag over:', dropNode.label)
}
const handleDragEnd = (
    draggingNode: Node,
    dropNode: Node,
    dropType: NodeDropType,
    ev: DragEvents
) => {
  console.log('tree drag end:', dropNode && dropNode.label, dropType)
}
const handleDrop = (
    draggingNode: Node,
    dropNode: Node,
    dropType: NodeDropType,
    ev: DragEvents
) => {
  console.log('tree drop:', dropNode.label, dropType)
}
const allowDrop = (draggingNode: Node, dropNode: Node, type: AllowDropType) => {
  if (dropNode.data.label === 'Level two 3-1') {
    return type !== 'inner'
  } else {
    return true
  }
}
const allowDrag = (draggingNode: Node) => {
  return !draggingNode.data.label.includes('Level three 3-1-1')
}

const data = [
  {
    label: '个人笔记',
    children: [
      {
        label: '学习计划',
        children: [
          {
            label: '编程学习',
            children: [
              { label: 'JavaScript 复习' },
              { label: 'Vue 3 项目' },
              { label: 'TypeScript 进阶' },
            ],
          },
          {
            label: '英语学习',
            children: [
              { label: '单词记忆' },
              { label: '听力练习' },
              { label: '口语提升' },
            ],
          },
        ],
      },
      {
        label: '生活日记',
        children: [
          {
            label: '旅行计划',
            children: [
              { label: '日本旅行' },
              { label: '欧洲之行' },
            ],
          },
          {
            label: '健康记录',
            children: [
              { label: '饮食计划' },
              { label: '运动日志' },
            ],
          },
        ],
      },
    ],
  },
  {
    label: '工作笔记',
    children: [
      {
        label: '项目记录',
        children: [
          {
            label: '项目A',
            children: [
              { label: '需求分析' },
              { label: '设计文档' },
              { label: '开发进度' },
            ],
          },
          {
            label: '项目B',
            children: [
              { label: '市场调研' },
              { label: '用户反馈' },
            ],
          },
        ],
      },
      {
        label: '会议纪要',
        children: [
          { label: '周会记录' },
          { label: '项目讨论会' },
        ],
      },
    ],
  },
  {
    label: '其他笔记',
    children: [
      {
        label: '读书笔记',
        children: [
          { label: '书名1' },
          { label: '书名2' },
        ],
      },
      {
        label: '灵感收集',
        children: [
          { label: '创意构思' },
          { label: '设计灵感' },
        ],
      },
    ],
  },
];

</script>

<template>
  <el-tree
      class="el-tree"
      :allow-drop="allowDrop"
      :allow-drag="allowDrag"
      :data="data"
      draggable
      node-key="id"
      @node-drag-start="handleDragStart"
      @node-drag-enter="handleDragEnter"
      @node-drag-leave="handleDragLeave"
      @node-drag-over="handleDragOver"
      @node-drag-end="handleDragEnd"
      @node-drop="handleDrop"

  >
    <template #default="{node,data }">
      <div v-if="data.children == null">
        <el-icon size="12">
          <Document/>
        </el-icon>
        <el-text style="margin-left: 6px;">
          {{ node.label }}
        </el-text>
      </div>
      <div v-else>

        <el-text>
          {{ node.label }}
        </el-text>
      </div>

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

:deep(.el-tree-node__content):focus  {
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


</style>