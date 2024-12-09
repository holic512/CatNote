<script setup lang="ts">
import {useDetailsState} from "@/views/User/Main/components/Sidebar/Pinia/DetailsState";
import {useRightSelectNodeId} from "@/views/User/Main/components/Sidebar/Pinia/RightSelectNodeId";
import {onMounted, watch} from "vue";

// 控制页面是否显示
const DetailsState = useDetailsState();

// 获取右键 选中节点的 信息
const rightSelect: any = useRightSelectNodeId();

// 除了 rightSelect 中 data信息之外,待获取信息有简介,占用空间,创建时间,修改时间

// 监听 当此页面被换出时 获取右键菜单的 信息
watch(() => DetailsState.DetailsIs, (newValue) => {
  // 当他等于 true
  if (newValue) {
    console.log(rightSelect.data);
  }
})

</script>

<template>
  <el-dialog v-model="DetailsState.DetailsIs" width="480" class="dialog-custom">

    <!-- 定制对话框的标题部分 -->
    <template #header="{ close, titleId, titleClass }">
      <el-text type="info">
        {{ rightSelect.data.label }}
        属性
      </el-text>
    </template>

    <!-- 对话框主体内容 -->
    <div class="dialog-body">

      <!-- 显示笔记图标与笔记名称输入框 -->
      <div class="note-info">
        <!-- 笔记图标 -->
        <div style="margin: 6px 8px 0 16px;" v-if="rightSelect.data.avatar == null ">
          <div v-if='rightSelect.data.type == "NOTE" '>
            <el-icon size="32">
              <Notebook/>
            </el-icon>
          </div>
          <div v-else-if='rightSelect.data.type == "FOLDER" '>
            <el-icon size="32">
              <Folder/>
            </el-icon>
          </div>
        </div>
        <div class="icon-container" v-else>
          {{ rightSelect.data.avatar }}
        </div>
        <!-- 笔记名称输入框 -->
        <div class="input-container">
          <!--          <el-input placeholder="新页面"></el-input> &lt;!&ndash; 设置占位符文本 &ndash;&gt;-->
          <el-text size="large"> {{ rightSelect.data.label }}</el-text>
        </div>
      </div>


      <!-- 分割线 -->
      <el-divider/>

      <div class="info-block">
        <div style="width: 80px;margin-left: 8px;">
          简介:
        </div>
        <div style="width:344px;">
          这篇笔记总结了我在工作中的一些经验与教训这篇笔记总结了我在工作中的一些经验与教训这篇笔记总结了我在工作中的一些经验与教训这篇笔记总结了我在工作中的一些经验与教训
        </div>
      </div>

      <el-divider style="margin: 16px 0 16px 0"/>

      <div class="info-block">
        <div style="width: 80px;margin-left: 8px;">
          文件类型:
        </div>
        <div>
          {{ rightSelect.data.type }}
        </div>
      </div>

      <el-divider style="margin: 16px 0 16px 0"/>


      <div class="info-block">
        <div style="width: 80px;margin-left: 8px;">
          占用空间:
        </div>
        <div>
          1.54KB(1,585字节)
        </div>
      </div>

      <el-divider style="margin: 16px 0 16px 0"/>

      <div class="info-block">
        <div style="width: 80px;margin-left: 8px;">
          创建时间:
        </div>
        <div>
          2024‎年‎12‎月‎4‎日，‏‎19:17:51
        </div>
      </div>

      <div class="info-block">
        <div style="width: 80px;margin-left: 8px;">
          修改时间:
        </div>
        <div>
          2024‎年‎12‎月‎4‎日，‏‎19:17:51
        </div>
      </div>

    </div>


  </el-dialog>
</template>

<style scoped>
/* 对话框自定义样式 */
.dialog-custom {
  padding: 12px;
}

.dialog-body {
  padding: 0 0 24px 0;
}

.note-info {
  display: flex;
  align-items: center;
  gap: 32px;
  margin-bottom: 16px;
}

.icon-container {
  font-size: 32px;
  margin-left: 12px;
}

.input-container {
  flex: 1;
}

.info-block {
  display: flex;
  align-items: center;
  margin-top: 12px;
}

.label {
  width: 80px;
  margin-left: 8px;
}

.value {
  flex: 1;
}

.el-divider {
  margin: 16px 0;
}
</style>