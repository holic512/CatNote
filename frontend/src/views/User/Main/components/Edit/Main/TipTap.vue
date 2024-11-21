<script setup lang="ts">
import '/src/fonts/alibabaFy.css'

import Tools from "./Tools/Tools.vue";
import ToCItem from "./ToCltem/ToCItem.vue";

import {Editor, EditorContent} from '@tiptap/vue-3'
import {ShallowRef} from "vue";
import BubbleMenu from "@/views/User/Main/components/Edit/Main/BubbleMenu/BubbleMenu.vue";

const editor: ShallowRef<Editor | undefined> = defineModel()


// 焦点恢复到编译器
const focusOnParagraph = () => {
  editor.value?.commands.focus(); // 将焦点设置到编辑器
}


</script>

<template>
  <!--编辑器 工具-->
  <Tools v-model="editor"/>

  <BubbleMenu v-model="editor"/>

  <el-scrollbar style="height: calc(100% - 44px)" @click="focusOnParagraph">
    <div class="editor-content">
      <editor-content :editor="editor" class="tiptap-editor"/>
    </div>
  </el-scrollbar>

  <!--  <div style="position: absolute; right: 18px; top: 164px; text-align: right;">-->
  <!--    <el-popover-->
  <!--        placement="left"-->
  <!--        title="目录"-->
  <!--        :width="250"-->
  <!--        trigger="hover"-->
  <!--    >-->
  <!--      <template #reference>-->
  <!--        <el-button class="m-2">H</el-button>-->
  <!--      </template>-->

  <!--      <template #default>-->
  <!--        <ToCItem v-if="editor" :editor="editor" :items="items"/>-->
  <!--      </template>-->
  <!--    </el-popover>-->
  <!--  </div>-->


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
  line-height: 1; /* 确保文字与背景有良好对比 */

  -webkit-font-smoothing: antialiased; /* 优化 Webkit 内核浏览器 */
  -moz-osx-font-smoothing: grayscale; /* 优化 macOS */

  hr {
    border: none;
    border-top: 2px solid #333; /* 更深的黑色线条 */
    cursor: pointer;
    padding: 0.2rem; /* 增加一些垂直空间 */
    margin: 1rem; /* 增加上下外边距，使分隔更明显 */
    transition: border-color 0.3s ease, box-shadow 0.3s ease; /* 添加过渡效果 */
    height: 10px; /* 确保高度为0，只显示边框 */

    &.ProseMirror-selectednode {
      border-top-color: #007BFF; /* 当选中时，使用蓝色线条 */
    }

    &:hover {
      border-top-color: #0056b3; /* 鼠标悬停时线条颜色变深 */
    }
  }

  h1 {
    font-size: 33px; /* 设置字体大小 */
    margin-bottom: 8px;
  }

  h2 {
    font-size: 31px; /* 设置字体大小 */
    margin-bottom: 8px;
  }

  h3 {
    font-size: 29px; /* 设置字体大小 */
    margin-bottom: 8px;
  }

  h4 {
    font-size: 27px; /* 设置字体大小 */
    margin-bottom: 8px;
  }

  h5 {
    font-size: 25px; /* 设置字体大小 */
    margin-bottom: 8px;
  }

  h6 {
    font-size: 23px; /* 设置字体大小 */
    margin-bottom: 8px;
  }

  img {
    max-width: 100%;
  }

  //引用块
  blockquote {
    border-left: 4px solid #555; /* 更粗的边框和更深的颜色 */

    margin-left: 1rem; /* 增加外边距，使引用更突出 */
    padding-left: 1rem; /* 增加内边距，使内容与边框有足够的空间 */
    background-color: #f9f9f9; /* 浅灰色背景，增加层次感 */
    margin-top: 0;
    margin-bottom: 0;

    p {
      height: 28px;
      display: flex;
      align-items: center;
      margin-top: 0;
      margin-bottom: 0;
    }

    span {
      height: 28px;
      display: flex;
      align-items: center;
      margin-top: 0;
      margin-bottom: 0;
    }

    &:hover {
      border-left-color: #007BFF; /* 鼠标悬停时边框颜色变为蓝色 */
    }
  }

  /* 单列样式 */
  ul,
  ol {
    margin: 2px;

    li p {

      margin-top: 6px;
      margin-bottom: 6px;
    }

    li div {
      margin-top: 0;
    }


    li label {
      margin: 0;
    }


    li {
      height: 32px;
      margin-top: 10px;

    }
  }


  /* 任务选中框样式 */
  ul[data-type="taskList"] {
    list-style: none;
    margin-left: 0;
    padding: 0;

    li {
      align-items: flex-start;
      display: flex;

      > label {
        flex: 0 0 auto;
        margin-right: 0.5rem;
        user-select: none;
      }

      > div {
        flex: 1 1 auto;
      }
    }

    input[type="checkbox"] {
      cursor: pointer;
    }

    ul[data-type="taskList"] {
      margin: 0;
    }
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
    margin-left: 1rem; /* 设置直接子元素的左边距 */
    margin-right: 1rem; /* 设置直接子元素的右边距 */
  }

  .ProseMirror-widget * {
    margin-top: auto; /* 对 ProseMirror 小部件应用自动顶部外边距 */
  }

  ul,
  ol {
    margin-left: 12px !important;
    padding-left: 28px;
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
    width: 1.25rem;
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
