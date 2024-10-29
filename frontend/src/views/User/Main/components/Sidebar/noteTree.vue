<script setup lang="ts">
import {getCurrentInstance, ref} from "vue";
import type Node from 'element-plus/es/components/tree/src/model/node'
import Button from 'primevue/button';
import {getNoteTree} from "./service/getNoteTree.ts";
import {Tree} from "./service/treeInterface.ts";
import {AddNote} from "@/views/User/Main/components/Sidebar/service/AddNote.ts";
import {ElMessage} from "element-plus";
import {getFolderIdByNoteId} from "@/views/User/Main/components/Sidebar/service/GetFolderIdByNoteId.ts";
import {CirclePlusFilled, Plus} from "@element-plus/icons-vue";
import {AddFolder} from "@/views/User/Main/components/Sidebar/service/AddFolder.ts";

const props = {
  label: 'label',
  children: 'zones',
  isLeaf: 'leaf',
}

const loadNode = async (node: Node, resolve: (data: Tree[]) => void) => {
  if (node.level === 0) {
    const data = await getNoteTree(null);
    return resolve(data);
  }

  const data = await getNoteTree(node.data.id);
  resolve(data);
};


// 用于存储 点击的选项内容
const clickData = ref<Tree>();

// 点击选项的 data
const handleNodeClick = (data: Tree) => {
  clickData.value = data
}

const {proxy} = getCurrentInstance();
// 代理 添加笔记操作
const proxyAddNote = async () => {
  const status = await AddNote(clickData.value);
  if (status == 200) {
    ElMessage.success("添加笔记成功")
    // 获取新笔记的详细信息（假设 AddNote 返回新笔记的 ID）
    const folderId = await getFolderIdByNoteId(clickData.value);
    let node = proxy.$refs.treeRef.getNode(folderId)
    node.loaded = false; // 标记为未加载
    node.expand();
  }
}
// 代理 添加文件夹操作
const proxyAddFolder = async () => {
  const status = await AddFolder(clickData.value);
  if (status == 200) {
    ElMessage.success("添加笔记成功")
    // 获取新笔记的详细信息（假设 AddNote 返回新笔记的 ID）
    const folderId = await getFolderIdByNoteId(clickData.value);
    let node = proxy.$refs.treeRef.getNode(folderId)

    console.log(node)

    node.loaded = false; // 标记为未加载
    node.expand();
  }
}

</script>

<template>
  <!--     笔记 控制按钮    -->
  <div class="sidebar-div" style="margin-bottom: 1px;">
    <div style="display: flex; justify-content: center; align-items: center; width: 50px"
    >
      <el-text size="small">笔记</el-text>
    </div>
    <!--  隔断  -->
    <div style="flex: 1"></div>
    <div>
      <Button class="sidebar-button" text icon="pi pi-ellipsis-h" size="small" severity="secondary"/>
    </div>
    <div>

      <el-dropdown trigger="click" size="small">
        <span class="el-dropdown-link">
          <Button class="sidebar-button" text icon="pi pi-plus" size="small" severity="secondary"/>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item :icon="Plus" @click="proxyAddNote">新建文档</el-dropdown-item>
            <el-dropdown-item :icon="CirclePlusFilled" @click="proxyAddFolder">新建文件夹</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>

    </div>
  </div>

  <div style="height: 40vh;width: 100%;">
    <el-scrollbar style="width: 100%;">
      <el-tree
          class="el-tree"
          lazy
          node-key="id"
          :props="props"
          :load="loadNode"
          :expand-on-click-node="false"
          @node-click="handleNodeClick"
          ref="treeRef"
      >
        <template #default="{node,data }">
          <div v-if='data.type == "NOTE" ' style="padding: 0">
            <el-icon size="12">
              <Notebook/>
            </el-icon>
          </div>
          <div v-else-if='data.type == "FOLDER" '>
            <el-icon size="12">
              <Folder/>
            </el-icon>
          </div>
          <el-text style="margin-left: 6px;">
            {{ node.label }}
          </el-text>


        </template>
      </el-tree>
    </el-scrollbar>
  </div>

</template>

<style scoped>

.sidebar-div {
  width: 100%;
  height: 30px;
  display: flex;
  align-items: center;
  border-radius: 4px; /* 添加圆角 */
  padding: 4px 2px 4px 2px; /* 增加内边距以增强按钮的点击区域 */
  transition: background-color 0.1s ease; /* 添加过渡效果 */
}


.sidebar-div:hover {
  background-color: #EFEFED;
}

.sidebar-button {
  width: 24px;
  height: 24px;
}

.sidebar-button:hover {
  background-color: #f8f9fa;
}


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