<script setup lang="ts">
import {ref} from 'vue';
import IconTable from "@/assets/icon/Bubble/blocks1/IconTable.vue";
import '../css/editTool-button.css'

const editor: any = defineModel();

const maxRows = 10; // 最大行数
const maxCols = 10; // 最大列数
const selectedRows = ref(0);
const selectedCols = ref(0);

// 选中网格单元格
const selectCell = (row, col) => {
  selectedRows.value = row;
  selectedCols.value = col;
};

// 插入表格到 Tiptap 编辑器
const insertTable = () => {
  if (editor) {
    editor.value.chain().focus().insertTable({rows: selectedRows.value, cols: selectedCols.value, withHeaderRow: true}).run();
  }
};
</script>


<template>
  <el-popover
      placement="bottom"
      :width="260"
      trigger="click"
      :hide-after="0"
      :popper-style="{padding: 8+'px'}"
  >
    <template #reference>
      <el-button class="tool-button" text>
        <el-icon size="16" color="#000000">
          <IconTable/>
        </el-icon>
      </el-button>
    </template>

    <div class="table-selector">
      <!-- 渲染网格 -->
      <div
          v-for="row in maxRows"
          :key="row"
          class="row"
      >
        <div
            v-for="col in maxCols"
            :key="col"
            class="cell"
            :class="{ active: row <= selectedRows && col <= selectedCols }"
            @mouseover="selectCell(row, col)"
            @click="insertTable"
        ></div>
      </div>
      <div style="margin-top: 4px;margin-left: 4px">
        <el-text size="small"> 当前表格:{{ selectedRows }} x {{ selectedCols }}</el-text>
      </div>
    </div>

  </el-popover>
</template>

<style scoped>
.table-selector {
  z-index: 1000;

  border: 1px solid #ddd;
  padding: 8px;
  border-radius: 4px;
}

.row {
  display: flex;
}

.cell {
  width: 20px;
  height: 20px;
  border: 1px solid #ddd;
  margin: 1px;
  background-color: #f5f5f5;
}

.cell.active {
  background-color: #74B5DA;
}

</style>