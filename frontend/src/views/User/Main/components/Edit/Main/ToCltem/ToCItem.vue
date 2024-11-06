<template>
  <div>
    <!-- 如果没有目录项，则显示空状态 -->
    <div class="empty-state" v-if="items.length === 0">
      <p>开始编辑文档以查看大纲。</p>
    </div>

    <div v-else>
      <!-- 遍历并渲染目录项 -->
      <div
          v-for="(item, i) in items"
          :key="item.id"
          @click="onItemClick($event, item.id)"
          :style="{ paddingLeft: `${item.level * 20 - 20}px` }"
      >
        <div class="mulu">
          <el-text> {{ item.textContent }}</el-text>
        </div>

      </div>
    </div>
  </div>
</template>

<script setup>
import {TextSelection} from '@tiptap/pm/state';
import {Editor} from "@tiptap/vue-3";

// 定义 props
const props = defineProps({
  items: {
    type: Array,
    default: () => [],
  },
  editor: {
    type: Editor,
    required: true,
  },
});

// 定义方法
const onItemClick = (e, id) => {

  e.preventDefault(); // 阻止默认行为

  if (props.editor) {
    // 查找对应的 DOM 元素，确保使用正确的选择器
    const element = document.querySelector(`[data-toc-id="${id}"]`);
    if (element) {
      const pos = props.editor.view.posAtDOM(element, 0); // 获取元素的位置


      const tr = props.editor.view.state.tr;
      tr.setSelection(new TextSelection(tr.doc.resolve(pos))); // 设置选中状态
      props.editor.view.dispatch(tr); // 更新编辑器视图
      props.editor.view.focus(); // 将焦点设置到编辑器

      // 平滑滚动到对应的元素位置
      window.scrollTo({
        top: element.getBoundingClientRect().top + window.scrollY,
        behavior: 'smooth',
      });
    } else {
      console.warn(`无法找到对应 ID 的元素: ${id}`);
    }
  }
};


</script>

<style scoped>
.mulu {
  display: flex;
  align-items: center;
  padding: 4px;
}

.mulu:hover {
  background-color: #EFEFED;
}


</style>
