<script setup lang="ts">
import {ref} from "vue";

// 控制是否显示
const AddClassDialogVisible = defineModel();

// 输入框内容
const categoryName = ref("");

// 关闭前的回调指令
const handleClose = () => {
  // 清空输入框
  categoryName.value = "";

  // 关闭
  AddClassDialogVisible.value = false;
}

const classTypeRadio = ref()
</script>

<template>
  <el-dialog
      v-model="AddClassDialogVisible"
      title="新建分类"
      width="450"
      :before-close="handleClose"
  >
    <el-form label-width="80px" style="padding:4px 20px 0 20px">

      <el-form-item label="分类名称">
        <el-input
            v-model="categoryName"
            placeholder="请输入分类名称"
            maxlength="20"
            show-word-limit
            style="width:260px"
        />
      </el-form-item>

      <el-form-item label="分类样式">
        <el-radio-group v-model="classTypeRadio">
          <el-radio value="1" >
            <el-tag size="small" type="primary">默认</el-tag>
          </el-radio>
          <el-radio value="2" >
            <el-tag size="small" type="info">基础</el-tag>
          </el-radio>
          <el-radio value="3" >
            <el-tag size="small" type="success">成功</el-tag>
          </el-radio>
          <el-radio value="4">
            <el-tag size="small" type="warning">重要</el-tag>
          </el-radio>
          <el-radio value="5" >
            <el-tag size="small" type="danger">严重</el-tag>
          </el-radio>
        </el-radio-group>

      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="handleClose">取消</el-button>
        <el-button type="primary">确认</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped>
.dialog-footer {
  text-align: right;
}

.classCheck {
  display: flex;
  align-items: center;
  gap: 4px;
}
</style>
