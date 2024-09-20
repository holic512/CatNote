<template>
  <div class="sidebar">
    <SidebarHeader :userName="userName" :userAvatar="userAvatar" />
    <TieredMenu ref="menu" :model="menuItems" :popup="true" :style="{ top: menuPosition.top, left: menuPosition.left }" />
    <div class="card flex justify-center">
      <Tree
          :value="nodes"
          :selectionKeys="selectedKeys"
          @node-select="onNodeSelect"
          @node-unselect="onNodeUnselect"
          @contextmenu="onFileContextMenu"
          @dragdrop="onDragDrop"
          draggable
          :dragdropScope="'tree'"
          :filter="true"
          :filterBy="'label'"
          :scrollHeight="'flex'"
          selectionMode="multiple"
      >
        <template #default="{ node }">
          <span>{{ node.label }}</span>
        </template>
      </Tree>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import Tree from 'primevue/tree';
import TieredMenu from 'primevue/tieredmenu';
import { NodeService } from '@/service/NodeService';
import SidebarHeader from './SidebarHeader.vue';
// import FileTree from "FileTree.vue";
// import SearchDialog from "./SearchDialog.vue";
// import FileContextMenu from "./FileContextMenu.vue";
export default {
  components: { SidebarHeader ,Tree, TieredMenu },
  setup() {
    const userName = '张三';
    const userAvatar = 'https://example.com/avatar.jpg'; // 用户头像地址

    const nodes = ref(null);
    const selectedKeys = ref({});
    const menu = ref(null);
    const menuItems = ref([
      {
        label: '移动到...',
        icon: 'pi pi-fw pi-external-link',
        items: [],
      },
      {
        label: '新建文件',
        icon: 'pi pi-file',
        command: () => createNewFile(),
      },
      {
        label: '新建文件夹',
        icon: 'pi pi-folder',
        command: () => createNewFolder(),
      },
      {
        label: '删除文件',
        icon: 'pi pi-trash',
        command: () => deleteSelectedFiles(),
      },
      {
        label: '重命名',
        icon: 'pi pi-pencil',
        command: () => renameSelectedFile(),
      },
    ]);
    const menuPosition = ref({top: '0px', left: '0px'});

    NodeService.getTreeNodes().then((data) => {
      nodes.value = data;
    });

    const onFileContextMenu = (event) => {
      event.preventDefault();
      menuPosition.value.top = `${event.clientY}px`;
      menuPosition.value.left = `${event.clientX}px`;
      menu.value.toggle(event);
    };

    const onNodeSelect = (event) => {
      selectedKeys.value[event.node.key] = true;
    };

    const onNodeUnselect = (event) => {
      delete selectedKeys.value[event.node.key];
    };

    const onDragDrop = (event) => {
      const draggedNodes = Object.keys(selectedKeys.value).map(key => nodes.value.find(node => node.key === key));
      const targetNode = event.dropNode;

      if (targetNode && draggedNodes.length > 0) {
        // 确保目标节点存在且是有效的父节点
        if (!targetNode.children) {
          targetNode.children = [];
        }

        // 将拖拽的节点添加到目标节点
        draggedNodes.forEach(draggedNode => {
          if (draggedNode) {
            targetNode.children.push(draggedNode);

            // 从原位置删除节点
            const index = nodes.value.findIndex(node => node.key === draggedNode.key);
            if (index !== -1) {
              nodes.value.splice(index, 1);
            }
          }
        });

        nodes.value = [...nodes.value]; // 强制更新视图
      }
    };

    const createNewFile = () => {
      const newName = prompt('请输入新的文件名', '新文件');
      if (newName) {
        nodes.value.push({key: Date.now().toString(), label: newName});
      }
    };

    const createNewFolder = () => {
      const newFolderName = prompt('请输入新的文件夹名', '新文件夹');
      if (newFolderName) {
        nodes.value.push({key: Date.now().toString(), label: newFolderName, children: []});
      }
    };

    const deleteSelectedFiles = () => {
      // 删除选中的文件逻辑
    };

    const renameSelectedFile = () => {
      // 重命名选中的文件逻辑
    };

    return {
      userName,
      userAvatar,
      nodes,
      selectedKeys,
      menu,
      menuItems,
      menuPosition,
      onFileContextMenu,
      onNodeSelect,
      onNodeUnselect,
      onDragDrop,
    };
  },
};
</script>

<style scoped>
.sidebar {
  width: 300px;
  height: 100%;
  background-color: #f9f9f9;
  border-right: 1px solid #e0e0e0;
  padding: 16px;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
  overflow-y: auto;
}
</style>