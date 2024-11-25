<script setup lang="ts">
import IconHeading1 from "@/assets/icon/Bubble/blocks1/IconHeading-1.vue"
import IconHeading2 from "@/assets/icon/Bubble/blocks1/IconHeading-2.vue"
import IconHeading3 from "@/assets/icon/Bubble/blocks1/IconHeading-3.vue"
import IconHeading4 from "@/assets/icon/Bubble/blocks1/IconHeading-4.vue"
import IconHeading5 from "@/assets/icon/Bubble/blocks1/IconHeading-5.vue"
import IconHeading6 from "@/assets/icon/Bubble/blocks1/IconHeading-6.vue"


import IconTodo from "@/assets/icon/Bubble/blocks1/IconTodo.vue";
import IconTable from "@/assets/icon/Bubble/blocks1/IconTable.vue";
import IconBlockquote from "@/assets/icon/Bubble/blocks1/IconBlockquote.vue";
import IconDivider from "@/assets/icon/Bubble/blocks1/IconDivider.vue";
import IconDetails from "@/assets/icon/Bubble/blocks1/IconDetails.vue";
import {DropdownInstance} from "element-plus";
import {ref} from "vue";
import {insertHorizontal} from "@/views/User/Main/components/Edit/Main/BubbleMenu/SetText/Service/insertHorizontal";
import {insertHeading} from "@/views/User/Main/components/Edit/Main/BubbleMenu/SetText/Service/insertHeading";

const editor: any = defineModel()

// 数据结构，包含 label, tag 和 icon 字段
const blocks1 = [
  // {label: '文本', tag: '编辑你的文本内容', icon: IconText},
  {label: '一级标题', tag: '设置一级标题的格式', icon: IconHeading1},
  {label: '二级标题', tag: '设置二级标题的格式', icon: IconHeading2},
  {label: '三级标题', tag: '设置三级标题的格式', icon: IconHeading3},
  {label: '四级标题', tag: '设置四级标题的格式', icon: IconHeading4},
  {label: '五级标题', tag: '设置五级标题的格式', icon: IconHeading5},
  {label: '六级标题', tag: '设置六级标题的格式', icon: IconHeading6},

  {label: '待办', tag: '管理任务和待办事项', icon: IconTodo},
  {label: '表格', tag: '插入一个表格', icon: IconTable},
  {label: '引用', tag: '引用其他内容', icon: IconBlockquote},
  {label: '分隔线', tag: '插入一条分隔线', icon: IconDivider},
  {label: '详细信息', tag: '查看详细信息', icon: IconDetails}
];


// 用来判断当前文本 是 block1中哪个状态  -  选中状态
const Blocks1SelectState = (label: string): boolean => {
  // 定义 状态组
  const Blocks1SelectStates = {
    "一级标题": editor.value?.isActive('heading', {level: 1}),
    "二级标题": editor.value?.isActive('heading', {level: 2}),
    "三级标题": editor.value?.isActive('heading', {level: 3}),
    "四级标题": editor.value?.isActive('heading', {level: 4}),
    "五级标题": editor.value?.isActive('heading', {level: 5}),
    "六级标题": editor.value?.isActive('heading', {level: 6}),
  };

  return Blocks1SelectStates[label] || false
};

const SelectToText = () => {
  if (editor.value?.isActive('heading', {level: 1})) {
    return "H1";
  } else if (editor.value?.isActive('heading', {level: 2})) {
    return "H2";
  } else if (editor.value?.isActive('heading', {level: 3})) {
    return "H3";
  } else if (editor.value?.isActive('heading', {level: 4})) {
    return "H4";
  } else if (editor.value?.isActive('heading', {level: 5})) {
    return "H5";
  } else if (editor.value?.isActive('heading', {level: 6})) {
    return "H6";
  } else {
    return "正文"; // 默认返回正文
  }
};

const SelectBlocks = {
  "一级标题": () => insertHeading(editor.value, 1),
  "二级标题": () => insertHeading(editor.value, 2),
  "三级标题": () => insertHeading(editor.value, 3),
  "四级标题": () => insertHeading(editor.value, 4),
  "五级标题": () => insertHeading(editor.value, 5),
  "六级标题": () => insertHeading(editor.value, 6),
};


const SelectBlock = (label: string) => {

  // 重设焦点 防止失焦点 出现 组件乱窜
  editor.value?.commands.focus();

  // 关闭下拉菜单
  handleClose()

  // 设置颜色
  SelectBlocks[label]();

}

const dropdown = ref<DropdownInstance>()

function handleClose() {
  if (dropdown.value) dropdown.value?.handleClose()
}

</script>

<template>
  <el-dropdown trigger="click" ref="dropdown" :hide-on-click="false">
    <el-button text style="width: 50px;height: 28px;padding-right: 6px;padding-left: 6px">
      <el-text tag="b" style="margin-right: 2px">
        {{ SelectToText() }}
      </el-text>
      <el-icon size="12">
        <ArrowDown/>
      </el-icon>
    </el-button>
    <template #dropdown>
      <div style="margin: 8px 0 2px 12px">
        <el-text size="small">转换为</el-text>
      </div>

      <el-scrollbar height="250">
        <div style="width: 200px; padding:  2px 12px 12px 12px">
          <div
              v-for="(block, index) in blocks1"
              :key="index"
          >
            <!--  文字提示  -->
            <el-tooltip placement="right" :show-after="200" :hide-after="0">
              <template #content>

                {{ block.tag }}

              </template>


              <div class="button-div" @click="SelectBlock(block.label)">
                <!-- 图标 -->
                <el-icon size="18" style="margin-right: 8px">
                  <component :is="block.icon"/>
                </el-icon>
                <!-- 文本和标签 -->
                <div style="padding: 2px">
                  <el-text>{{ block.label }}</el-text>
                </div>

                <div style="flex: 1"/>

                <!--  选中图标  -->
                <el-icon size="14" v-if="Blocks1SelectState(block.label)">
                  <Select/>
                </el-icon>

              </div>


            </el-tooltip>
          </div>
        </div>
      </el-scrollbar>
    </template>
  </el-dropdown>
</template>

<style scoped>
.button-div {
  display: flex;
  align-items: center;
  padding: 2px;
  border-radius: 6px;
}

.button-div:hover {
  background-color: #EFEFED;
}
</style>