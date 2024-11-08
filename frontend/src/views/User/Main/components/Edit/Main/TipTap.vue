<script setup lang="ts">
import '/src/fonts/alibabaFy.css'

import Tools from "./Tools/Tools.vue";
import ToCItem from "./ToCltem/ToCItem.vue";


import StarterKit from '@tiptap/starter-kit'
import {BubbleMenu, EditorContent, useEditor} from '@tiptap/vue-3'
import DragHandle from '@tiptap-pro/extension-drag-handle'
import NodeRange from '@tiptap-pro/extension-node-range'
import {onBeforeUnmount, ref} from "vue";
import Underline from '@tiptap/extension-underline'
import {getHierarchicalIndexes, TableOfContents} from "@tiptap-pro/extension-table-of-contents";
import TextStyle from '@tiptap/extension-text-style'
import {Color} from "@tiptap/extension-color";
import Highlight from '@tiptap/extension-highlight'
import Placeholder from '@tiptap/extension-placeholder'
import Image from '@tiptap/extension-image'
import FileHandler from '@tiptap-pro/extension-file-handler'
import fileHandlerConfig from "./Config/FileHandler.ts";

const editor = useEditor({
  extensions: [

    StarterKit,

    // 启用下划线扩展
    Underline,

    // 文本样式
    TextStyle,

    Placeholder.configure({
      placeholder: '创造你的无限可能',
    }),

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

    // 图片
    Image,

    // 推拽 粘贴
    FileHandler.configure(fileHandlerConfig),

    // node
    NodeRange.configure({
      // allow to select only on depth 0
      // depth: 0,
      key: null,
    }),

    // 拖拽区块
    DragHandle.configure({
      render() {
        const element = document.createElement('div')

        element.classList.add('custom-drag-handle')

        return element
      },
    }),
  ],

  content: "",

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

// 销毁编辑器实例
onBeforeUnmount(() => {
  editor.value?.destroy()
})

// 焦点恢复到编译器
const focusOnParagraph = () => {
  editor.value?.commands.focus(); // 将焦点设置到编辑器
}

// 定义目录项
const items = ref([]);

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


<style lang="scss">
/* 样式用于编辑器的主要内容区域 */
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

/* 去掉编辑器段落的默认选中样式 */
.editor-content p:focus {
  outline: none;
}

/* 设置编辑器内容的样式 */
.tiptap-editor {
  width: 750px; /* 设置编辑区域的宽度 */
  font-family: 'alibabaFy', serif; /* 使用自定义字体 */
  font-size: 20px; /* 设置字体大小 */

  h1 {
    font-size: 38px; /* 设置字体大小 */
    margin-bottom: 8px;
  }

  h2 {
    font-size: 35px; /* 设置字体大小 */
    margin-bottom: 8px;
  }

  h3 {
    font-size: 32px; /* 设置字体大小 */
    margin-bottom: 8px;
  }

  h4 {
    font-size: 29px; /* 设置字体大小 */
    margin-bottom: 8px;
  }

  h5 {
    font-size: 26px; /* 设置字体大小 */
    margin-bottom: 8px;
  }

  h6 {
    font-size: 23px; /* 设置字体大小 */
    margin-bottom: 8px;
  }
  img{
    max-width: 100%;
  }
}

/* 设置选中文本的背景颜色 */
::selection {
  background-color: #70CFF850;
}

/* ProseMirror 编辑器的通用样式 */
.ProseMirror {
  padding: 1rem 1rem 1rem 0; /* 设置内容的内边距 */

  * {
    margin-top: 0.75em; /* 设置每个子元素的顶部外边距 */
  }

  > * {
    //margin-left: 3rem; /* 设置直接子元素的左边距 */
  }

  .ProseMirror-widget * {
    margin-top: auto; /* 对 ProseMirror 小部件应用自动顶部外边距 */
  }

  ul,
  ol {
    padding: 0 1rem; /* 为无序列表和有序列表添加左右内边距 */
  }
}

/* 选区范围的样式 */
.ProseMirror-noderangeselection {
  *::selection {
    background: transparent; /* 设置选区范围背景色为透明 */
  }

  * {
    caret-color: transparent; /* 将插入符号颜色设为透明 */
  }
}

/* 设置被选中节点或选区的样式 */
.ProseMirror-selectednode,
.ProseMirror-selectednoderange {
  position: relative;

  &::before {
    position: absolute;
    pointer-events: none; /* 禁止指针事件 */
    z-index: -1; /* 设置层级 */
    content: '';
    top: -0.25rem;
    left: -0.25rem;
    right: -0.25rem;
    bottom: -0.25rem;
    background-color: #70CFF850; /* 设置背景颜色 */
    border-radius: 0.2rem; /* 圆角 */
  }
}

/* 自定义拖拽手柄样式 */
.custom-drag-handle {
  &::after {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 1rem;
    height: 1.25rem;
    content: '⠿'; /* 显示拖拽手柄的内容 */
    font-weight: 700;
    cursor: grab; /* 鼠标样式为抓取 */
    background: #0D0D0D10; /* 背景色 */
    color: #0D0D0D50; /* 字体颜色 */
    border-radius: 0.25rem; /* 圆角 */

  }
}

/* 编辑器占位符样式 */
.tiptap {
  /* 顶部占位符 */
  p.is-editor-empty::before {
    color: #9B9A97; /* 占位符文本颜色 */
    content: attr(data-placeholder); /* 占位符内容 */
    float: left;
    height: 0;
    pointer-events: none; /* 禁用鼠标事件 */
  }

  /* 新行的占位符 */
  .is-empty::before {
    color: #9B9A97;
    content: attr(data-placeholder);
    float: left;
    height: 0;
    pointer-events: none;
  }
}
</style>
