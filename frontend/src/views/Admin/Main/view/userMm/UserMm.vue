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
import fetchInitialPageData from "./components/TableView/fetchInitialPageData.ts";
import {fetchPageData} from "./components/TableView/fetchPageData.ts";
import {calculateRows} from "./components/TableView/calculateRows.ts";
import {getStatusMsg, getStatusType} from "./components/TableView/getStatusType.ts";
import AddUser from "./components/AddUser/addUser.vue";
import {debounceImmediate} from "../../../../../util/debounce.ts";
import {ElMessage} from "element-plus";
import {BatchDeleteUser} from "./components/TableView/batchDeleteUser.ts";
import OnlineUser from "./components/OnlineUser/OnlineUser.vue";
import {onlineUserCount} from "@/views/Admin/Main/view/userMm/components/OnlineUser/OnlineUserCount.ts";

// 搜素框数据
const value1 = ref(null);

// 计算表格显示条数
const minHeight = 720;  // 基准高度
const stepHeight = 45;  // 每个高度增加的间隔

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
      "admin/userMm/getUserCount",
  ).then((response) => {
    userCount.value = response.data.data;
    maxPage.value = Math.ceil(userCount.value / nowRow.value);
  });

  // 获取初始数据 固定获取第一页的数据
  products.value = await fetchInitialPageData(nowRow.value);

  // 挂载 页面尺寸监听器
  window.addEventListener('resize', handleResize);

  // 获取在线用户数目
  OUserCount.value = await onlineUserCount()
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
  return `${475 + (nowRow.value - 10) * 45}px`;
});


// 制定分页逻辑
enum pageTurn { // 翻页行为枚举
  FirstPage,
  PreviousPage,
  NextPage,
  LastPage
}

const turnPage = async (turn: pageTurn) => {
  switch (turn) {
    case pageTurn.FirstPage:
      if (nowPage.value != 1) {
        nowPage.value = 1;
        products.value = await fetchPageData(nowRow.value, nowPage.value);
      } else {
        ElMessage.warning("已经是第一页了")
      }
      break

    case pageTurn.PreviousPage:
      if (nowPage.value > 1) {
        nowPage.value = nowPage.value - 1;
        products.value = await fetchPageData(nowRow.value, nowPage.value);
      } else {
        ElMessage.warning("已经是第一页了")
      }
      break

    case pageTurn.NextPage:
      if (nowPage.value < maxPage.value) {
        nowPage.value = nowPage.value + 1;
        products.value = await fetchPageData(nowRow.value, nowPage.value);
      } else {
        ElMessage.warning("已经是最后一页了")
      }
      break
    case pageTurn.LastPage:
      if (nowPage.value != maxPage.value) {
        nowPage.value = maxPage.value;
        products.value = await fetchPageData(nowRow.value, nowPage.value);
      } else {
        ElMessage.warning("已经是最后一页了")
      }
      break
    default:
      console.error(`Unknown turn: ${turn}`);
  }
}
const handleDebouncedTurnPage = debounceImmediate(turnPage, 200)

// 刷新逻辑
const refresh = async () => {
  await axios.get(
      "admin/userMm/getUserCount",
  ).then((response) => {
    userCount.value = response.data.data;
    maxPage.value = Math.ceil(userCount.value / nowRow.value);
  });
  // 当 重新获取后 最大页数 小于当前页数 则查询最后一页
  if (maxPage.value < nowPage.value) {
    products.value = await fetchPageData(nowRow.value, maxPage.value);
  } else {
    products.value = await fetchPageData(nowRow.value, nowPage.value);
  }

  ElMessage.success("刷新成功")
}
// 刷新逻辑 的 防抖函数
const handleDebouncedRefresh = debounceImmediate(refresh, 1000);

// 选择逻辑
const selectedProduct = ref();
// 批量删除/防抖
const batchDelete = async () => {
  if (selectedProduct.value == null) {
    ElMessage.warning("选择为空")
    return;
  }
  const ids = selectedProduct.value.map(product => product.id);
  const status = await BatchDeleteUser(ids);
  if (status === 200) {
    ElMessage.success("删除成功")
  } else {
    ElMessage.error("无法连接服务器");
  }
}
const handleDebouncedBatchDelete = debounceImmediate(batchDelete, 1000);


// 控制添加用户页面
const addUserVisible = ref<boolean>(false);

// 控制在线用户页面
const onlineUserVisible = ref<boolean>(false);

// 在线用户数目变量
const OUserCount = ref(0);

</script>

<template>
  <el-scrollbar height="100%">
    <div class="common-layout">


      <div class="title-container">

        <el-row justify="space-between">
          <el-col :span="12" class="title">
            <h1>所有用户</h1>
            <p>这个列表可以对所有用户进行管理</p>
          </el-col>

          <!--在线用户管理按钮-->
          <el-col :span="6" style="display: flex;align-items: center; /* 垂直居中 */justify-content: right">
            <Button type="button" badgeSeverity="contrast" outlined size="small"
                    style="margin-left: 8px;width: 130px"
                    @click="onlineUserVisible = true"
            >
              <i class="pi pi-circle-fill" style="color: #22C55E"></i>
              <el-text tag="b">
                {{ OUserCount }}
              </el-text>
              <el-text>
                在线用户
              </el-text>
            </Button>
          </el-col>
        </el-row>

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
                      v-tooltip.bottom="{ value: '添加用户', showDelay: 1000, hideDelay: 300 }"
                      @click="addUserVisible = true"/>

              <Button icon="pi pi-trash" severity="secondary" outlined size="small"
                      @click="handleDebouncedBatchDelete"
                      v-tooltip.bottom="{ value: '删除选中用户', showDelay: 1000, hideDelay: 300 }"/>

              <Button icon="pi pi-spinner" severity="secondary" outlined size="small"
                      @click="handleDebouncedRefresh"
                      v-tooltip.bottom="{ value: '刷新', showDelay: 1000, hideDelay: 300 }"/>

              <el-divider direction="vertical"/>
              <Tag severity="info">用户数: {{ userCount }}</Tag>
              <Tag style="width: 140px">页数: {{ nowPage }} of {{ maxPage }}</Tag>

              <!--翻页按键-->
              <Button icon="pi pi-angle-double-left" severity="secondary" outlined size="small"
                      @click="handleDebouncedTurnPage(pageTurn.FirstPage)"
                      v-tooltip.bottom="{ value: '跳转第一页', showDelay: 1000, hideDelay: 300 }"/>
              <Button icon="pi pi-angle-left" severity="secondary" outlined size="small"
                      @click="handleDebouncedTurnPage(pageTurn.PreviousPage)"
                      v-tooltip.bottom="{ value: '上一页', showDelay: 1000, hideDelay: 300 }"/>
              <Button icon="pi pi-angle-right" severity="secondary" outlined size="small"
                      @click="handleDebouncedTurnPage(pageTurn.NextPage)"
                      v-tooltip.bottom="{ value: '下一页', showDelay: 1000, hideDelay: 300 }"/>
              <Button icon="pi pi-angle-double-right" severity="secondary" outlined size="small"
                      @click="handleDebouncedTurnPage(pageTurn.LastPage)"
                      v-tooltip.bottom="{ value: '跳转最后一页', showDelay: 1000, hideDelay: 300 }"/>

            </div>
          </el-col>
        </el-row>
      </div>

      <!--  表格  -->
      <div class="table-container">

        <DataTable v-model:selection="selectedProduct" :value="products" stripedRows dataKey="uid"
                   tableStyle="min-width: 850px;" size="small" :style="{ minHeight: dynamicHeight }">
          <Column selectionMode="multiple" headerStyle="width: 4%" position="fixed"></Column>
          <Column field="id" header="id" headerStyle="width: 8%"></Column>
          <Column field="uid" header="Uid" headerStyle="width: 16%"></Column>
          <Column field="username" header="用户名" headerStyle="width: 20%"></Column>
          <Column field="email" header="邮箱地址" headerStyle="width: 32%"></Column>
          <Column field="status" header="状态" headerStyle="width: 12%">
            <template #body="{ data }">
              <Tag :value="getStatusMsg(data.status)" :severity="getStatusType(data.status)"/>
            </template>
          </Column>

          <Column header="更多" headerStyle="width: 8%">
            <template #body>
              <Button type="button" icon="pi pi-ellipsis-h" rounded outlined style=" height: 32px;width: 32px"/>
            </template>
          </Column>
        </DataTable>


      </div>

    </div>
  </el-scrollbar>

  <!--  添加用户页面  -->
  <AddUser v-model="addUserVisible"/>

  <!--  用户信息编辑页面  -->


  <!--  在线用户页面  -->
  <OnlineUser v-model="onlineUserVisible"/>

</template>

<style scoped>
.common-layout {
  height: 100%;
  padding-left: 1px;
  padding-right: 15px;
  background-color: white;
}

.title-container {
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
  background-color: white;
  margin-top: 15px;
  width: 100%;
  border-radius: 10px;
  box-shadow: 0 0 0 1px #D9D9D9;
  padding: 4px;
}

</style>