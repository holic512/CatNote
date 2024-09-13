<script setup lang="ts">
//组件加载
import Button from 'primevue/button';
import IconField from 'primevue/iconfield';
import InputIcon from 'primevue/inputicon';
import InputText from "primevue/inputtext";
import Tag from 'primevue/tag'

import {computed, onBeforeUnmount, onMounted, ref} from "vue";
// 表格组件
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import axios from "../../../../../axios";
import fetchInitialPageData from "./fetchInitialPageData.ts";
import {fetchPageData} from "./fetchPageData.ts";
import {calculateRows} from "./calculateRows.ts";
import {getSeverity} from "./getSeverity.ts";

// 搜素框数据
const value1 = ref(null);

// 计算表格显示条数
const minHeight = 795;  // 基准高度
const stepHeight = 55;  // 每个高度增加的间隔

// 当前行数
let nowRow = ref(10);

// 存储用户总数
const userCount = ref(0);

// 最大页数
const maxPage = ref(1);

// 当前页数
const nowPage = ref(1);

// 表格数据
const products = ref([]);

// 钩子函数
onMounted(async () => {
  // 获取当前尺寸 所能显示的 行数
  nowRow.value = calculateRows(minHeight, stepHeight);

  // 获取用户总数 并 计算最大页数
  await axios.get(
      "admin/userMm/getUserCount"
  ).then((response) => {
    userCount.value = response.data.data;
    maxPage.value = Math.ceil(userCount.value / nowRow.value);
  });

  // 获取初始数据 固定获取第一页的数据
  products.value = await fetchInitialPageData(nowRow.value);

  // 挂载 页面尺寸监听器
  window.addEventListener('resize', handleResize);
})

// 在组件销毁时移除监听器
onBeforeUnmount(() => {
  window.removeEventListener('resize', handleResize);
});

// 监听窗口大小变化并防抖
const DEBOUNCE_DELAY = 100; // 防抖延时常量
let resizeTimeout: ReturnType<typeof setTimeout>;
const handleResize = async () => {
  clearTimeout(resizeTimeout);
  resizeTimeout = setTimeout(async () => {
    const rows = calculateRows(minHeight, stepHeight);

    if (rows !== nowRow.value) {
      // 更新行数和最大页数
      nowRow.value = rows;
      maxPage.value = Math.ceil(userCount.value / nowRow.value);

      // 调整当前页数，确保不会超出最大页数
      if (nowPage.value > maxPage.value) {
        nowPage.value = maxPage.value;
      }
      // 根据当前行数和页数重新获取数据
      products.value = await fetchPageData(nowRow.value, nowPage.value);
    }
  }, DEBOUNCE_DELAY);
};

// 计算 table 的动态高度 达到适配
const dynamicHeight = computed(() => {
  return `${550 + (nowRow.value - 10) * 50}px`;
});


// 制定分页逻辑
enum pageTurn { // 翻页行为枚举
  UP,
  DOWN
}

const turnPage = async (turn: pageTurn) => {
  switch (turn) {
    case pageTurn.UP:
      if (nowPage.value < maxPage.value) {
        nowPage.value = nowPage.value + 1;
        products.value = await fetchPageData(nowRow.value, nowPage.value);
      }
      break
    case pageTurn.DOWN:
      if (nowPage.value > 1) {
        nowPage.value = nowPage.value - 1;
        products.value = await fetchPageData(nowRow.value, nowPage.value);
      }
      break
    default:
      console.error(`Unknown turn: ${turn}`);
  }
}

// 选择逻辑
const selectedProduct = ref();


</script>

<template>
  <el-scrollbar height="100%">
    <div class="common-layout">


      <div class="title">
        <h1>所有用户</h1>
        <p>这个列表可以对所有用户进行管理</p>
      </div>

      <!--  工具栏  -->
      <div class="description-container">
        <el-row>
          <el-col :span="4">
            <IconField>
              <InputIcon class="pi pi-search custom-icon"/>
              <InputText v-model="value1" placeholder="Search" class="custom-input"/>
            </IconField>
          </el-col>

          <el-col :span="20">
            <div class="col-right-part">
              <Button icon="pi pi-plus" severity="secondary" outlined size="small"
                      v-tooltip.bottom="{ value: '添加用户', showDelay: 1000, hideDelay: 300 }"/>
              <Button icon="pi pi-trash" severity="secondary" outlined size="small"
                      v-tooltip.bottom="{ value: '删除选中用户', showDelay: 1000, hideDelay: 300 }"/>
              <Button icon="pi pi-spinner" severity="secondary" outlined size="small"
                      v-tooltip.bottom="{ value: '刷新', showDelay: 1000, hideDelay: 300 }"/>
              <el-divider direction="vertical"/>
              <Tag severity="info">用户数: {{ userCount }}</Tag>
              <Tag>页数: {{ nowPage }} of {{ maxPage }}</Tag>

              <!--翻页按键-->
              <Button icon="pi pi-angle-left" severity="secondary" outlined size="small"
                      @click="turnPage(pageTurn.DOWN)"/>
              <Button icon="pi pi-angle-right" severity="secondary" outlined size="small"
                      @click="turnPage(pageTurn.UP)"/>
            </div>
          </el-col>
        </el-row>
      </div>

      <!--  表格  -->
      <div class="table-container">

        <DataTable v-model:selection="selectedProduct" :value="products" stripedRows dataKey="uid"
                   tableStyle="min-width: 50rem;" size="small" :style="{ minHeight: dynamicHeight }">
          <Column selectionMode="multiple" headerStyle="width: 3rem"></Column>
          <Column field="id" header="id" headerStyle="width: 3rem"></Column>
          <Column field="uid" header="Uid"></Column>
          <Column field="username" header="用户名"></Column>
          <Column field="email" header="邮箱地址"></Column>
          <Column field="status" header="状态">
            <template #body="{ data }">
              <Tag :value="data.status" :severity="getSeverity(data.status)"/>
            </template>
          </Column>

          <Column header="更多">
            <template #body>
              <Button type="button" icon="pi pi-ellipsis-h" rounded outlined size="small"/>
            </template>
          </Column>
        </DataTable>


      </div>

    </div>
  </el-scrollbar>
</template>

<style scoped>
.common-layout {
  height: 100%;
  padding-left: 1px;
  padding-right: 15px;
}

.title {
  margin: 0; /* 去掉默认外边距 */
}

.title h1 {
  color: #334155; /* 标题颜色 */
  font-size: 22px; /* 字体大小 */
  margin: 0; /* 去掉默认外边距 */
  font-weight: 700; /* 字体加粗 */
}

.title p {
  color: #64748b; /* 段落颜色 */
  font-size: 14px; /* 字体大小 */
  margin: 0; /* 去掉默认外边距 */
}

.description-container {
  margin-top: 15px;
}

/* 图标样式 */
.custom-icon {
  font-size: 16px; /* 调整图标大小 */
}

/* 输入框样式 */
.custom-input {
  font-size: 14px; /* 字体大小 */
  padding: 5px 10px; /* 内边距 */
  height: 32px; /* 高度 */
}

.col-right-part {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  gap: 10px; /* 调整间隙大小 */
}

.table-container {
  margin-top: 15px;
  width: 100%;
  border-radius: 10px;
  box-shadow: 0 0 0 1px rgba(0, 0, 0, 0.1);
  padding: 4px;
}

</style>