<template>
  <div class="sidebar">
    <TieredMenu ref="menu" :model="menuItems" :popup="true" :style="{ top: menuPosition.top, left: menuPosition.left }" />
    <div class="card flex justify-center">
      <Tree
          :value="nodes"
          :selectionKey="selectedKey"
          @node-select="onNodeSelect"
          @node-unselect="onNodeUnselect"
          @contextmenu="onFileContextMenu"
          @dragdrop="onDragDrop"
      :draggable="true"
      :dragdropScope="'tree'"
      :filter="true"
      :filterBy="'label'"
      :scrollHeight="'flex'"
      selectionMode="single"
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

export default {
  components: { Tree, TieredMenu },
  setup() {
    const nodes = ref(null);
    const selectedKey = ref(null);
    const menu = ref(null);
    const menuItems = ref([
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
    const menuPosition = ref({ top: '0px', left: '0px' });

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
      selectedKey.value = event.node.key;
    };

    const onNodeUnselect = (event) => {
      selectedKey.value = null;
    };

    const onDragDrop = (event) => {
      const draggedNode = event.dragNode;
      const targetNode = event.dropNode;

      // 处理节点位置更改逻辑
      if (targetNode) {
        // 更新节点的位置，比如添加到目标节点的子节点中
        targetNode.children = targetNode.children || [];
        targetNode.children.push(draggedNode);

        // 从原位置移除节点
        const index = nodes.value.findIndex(node => node.key === draggedNode.key);
        if (index !== -1) {
          nodes.value.splice(index, 1);
        }

        // 更新树结构
        nodes.value = [...nodes.value];
      }
    };

    return {
      nodes,
      selectedKey,
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
