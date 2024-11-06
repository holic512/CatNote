<script setup lang="ts">
import '/src/fonts/alibabaFy.css'

import Tools from "@/views/User/Main/components/Edit/Main/Tools/Tools.vue";
import ToCItem from "@/views/User/Main/components/Edit/Main/ToCltem/ToCItem.vue";

import {onBeforeUnmount, ref} from "vue";

import {useEditor, EditorContent, BubbleMenu} from '@tiptap/vue-3'
import StarterKit from '@tiptap/starter-kit'
import Underline from '@tiptap/extension-underline'
import {getHierarchicalIndexes, TableOfContents} from "@tiptap-pro/extension-table-of-contents";
import TextStyle from '@tiptap/extension-text-style'
import {Color} from "@tiptap/extension-color";
import Highlight from '@tiptap/extension-highlight'


const editor = useEditor({
  content: '',
  extensions: [
    // 基础包
    StarterKit,
    // 启用下划线扩展
    Underline,
    // 文本样式
    TextStyle,
    // 颜色
    Color,
    // 高亮
    Highlight.configure({
      multicolor: true,
    }),

    // 目录
    TableOfContents.configure({
      getIndex: getHierarchicalIndexes,
      onUpdate(content) {
        // 在这里更新目录项
        items.value = content;
      },
    }),

  ],

  // 监听粘贴事件
  onPaste: (e, slice) => {
    // 获取剪贴板中的纯文本内容
    const text = slice.content.textBetween(0, slice.content.size, '\n', '\n')

    // 通过正则去掉文本中的 HTML 标签
    const cleanText = text.replace(/<\/?[^>]+(>|$)/g, "") // 去除所有HTML标签

    // 用纯文本替换当前选区
    const tr = editor.value?.view.state.tr.replaceSelectionWith(
        editor.value?.view.state.schema.text(cleanText) // 用清除样式的文本替换
    )

    editor.value?.view.dispatch(tr)

    // 如果希望取消默认的粘贴行为（即不插入原样内容）
    e.preventDefault()
  },

})

// 定义目录项
const items = ref([]);

// 焦点恢复到编译器
const focusOnParagraph = () => {

  // // 当文本为空的时候 点击空白处 重置焦点
  // if (editor.value?.getText() == "") {
  //   editor.value?.commands.focus(); // 将焦点设置到编辑器
  // }

  editor.value?.commands.focus(); // 将焦点设置到编辑器
}

// 销毁编辑器实例
onBeforeUnmount(() => {
  editor.value?.destroy()
})
</script>

<template>

  <!--编辑器 工具-->
  <Tools v-model="editor"/>

  <bubble-menu
      :editor="editor"
      :tippy-options="{ duration: 100 }"
      v-if="editor"
  >
    <div class="bubble-menu">
      <button @click="editor.chain().focus().toggleBold().run()" :class="{ 'is-active': editor.isActive('bold') }">
        Bold
      </button>
      <button @click="editor.chain().focus().toggleItalic().run()" :class="{ 'is-active': editor.isActive('italic') }">
        Italic
      </button>
      <button @click="editor.chain().focus().toggleStrike().run()" :class="{ 'is-active': editor.isActive('strike') }">
        Strike
      </button>
    </div>
  </bubble-menu>


  <el-scrollbar style="height: calc(100% - 44px)" @click="focusOnParagraph">
    <div class="editor-content">
      <editor-content :editor="editor" class="tiptap-editor"/>
    </div>
  </el-scrollbar>


  <div style="position: absolute; right: 18px; top: 164px; text-align: right;">
    <el-popover
        placement="left"
        title="目录"
        :width="250"
        trigger="hover"
    >
      <template #reference>
        <el-button class="m-2">H</el-button>
      </template>

      <template #default>
        <ToCItem v-if="editor" :editor="editor" :items="items"/>
      </template>
    </el-popover>
  </div>

</template>

<style scoped>
.editor-content {
  outline: none; /* 去掉选中时的黑框 */
  cursor: text; /* 鼠标悬停时显示文本光标 */


  flex: 1;

  display: flex;
  justify-content: center;

  width: 100%;
  height: 100%;

}


/* 去掉文本选中时的轮廓 */
.editor-content *:focus {
  outline: none;
}

/* 去掉编辑器的默认文本选中样式 */
.editor-content p:focus {
  outline: none;
}

/* 设置编辑器内容的字体 */
.tiptap-editor {
  width: 700px;
  font-family: 'alibabaFy', serif; /* 替换为你想要的字体 */
  font-size: 20px; /* 设置字体大小 */
  line-height: 1.5; /* 设置行高 */

}
</style>
