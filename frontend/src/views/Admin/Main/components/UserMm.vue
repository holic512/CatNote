<script setup lang="ts">
//组件加载
import Button from 'primevue/button';
import IconField from 'primevue/iconfield';
import InputIcon from 'primevue/inputicon';
import InputText from 'primevue/InputText'
import Tag from 'primevue/tag'

import {ref, onMounted} from "vue";

// 搜素
const value1 = ref(null);

// 表格
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';

onMounted(() => {

});

const products = ref([
  {
    id: 1,
    code: 'P001',
    username: 'user1',
    nickname: '产品1',
    email: 'user1@example.com',
    status: 'active'
  },
  {
    id: 2,
    code: 'P002',
    username: 'user2',
    nickname: '产品2',
    email: 'user2@example.com',
    status: 'inactive'
  },
  {
    id: 3,
    code: 'P003',
    username: 'user3',
    nickname: '产品3',
    email: 'user3@example.com',
    status: 'active'
  },
  {
    id: 4,
    code: 'P003',
    username: 'user3',
    nickname: '产品3',
    email: 'user3@example.com',
    status: 'active'
  }, {
    id: 5,
    code: 'P003',
    username: 'user3',
    nickname: '产品3',
    email: 'user3@example.com',
    status: 'active'
  }, {
    id: 6,
    code: 'P003',
    username: 'user3',
    nickname: '产品3',
    email: 'user3@example.com',
    status: 'active'
  }, {
    id: 7,
    code: 'P003',
    username: 'user3',
    nickname: '产品3',
    email: 'user3@example.com',
    status: 'active'
  }, {
    id: 8,
    code: 'P003',
    username: 'user3',
    nickname: '产品3',
    email: 'user3@example.com',
    status: 'active'
  }, {
    id: 9,
    code: 'P003',
    username: 'user3',
    nickname: '产品3',
    email: 'user3@example.com',
    status: 'active'
  },
  {
    id: 10,
    code: 'P003',
    username: 'user3',
    nickname: '产品3',
    email: 'user3@example.com',
    status: 'active'
  },


]);

const selectedProduct = ref();

const getSeverity = (status: string) => {
  switch (status) {
    case 'active':
      return 'success';

    case 'inactive':
      return 'info';

  }
}

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
              <Button label="1 of 15" severity="secondary" outlined size="small"/>
              <Button icon="pi pi-angle-left" severity="secondary" outlined size="small"/>
              <Button icon="pi pi-angle-right" severity="secondary" outlined size="small"/>
            </div>
          </el-col>
        </el-row>
      </div>

      <!--  表格  -->
      <div class="table-container">

        <DataTable v-model:selection="selectedProduct" :value="products" stripedRows dataKey="id"
                   tableStyle="min-width: 50rem;" size="small">
          <Column selectionMode="multiple" headerStyle="width: 3rem"></Column>
          <Column field="code" header="Uid"></Column>
          <Column field="username" header="用户名"></Column> <!-- 假设有一个 username 字段 -->
          <Column field="nickname" header="昵称"></Column>
          <Column field="email" header="邮箱地址"></Column> <!-- 假设有一个 email 字段 -->
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