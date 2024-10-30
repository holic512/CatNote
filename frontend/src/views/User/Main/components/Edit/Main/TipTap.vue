<script setup lang="ts">
import {useEditor, EditorContent} from '@tiptap/vue-3'
import StarterKit from '@tiptap/starter-kit'
import Tools from "@/views/User/Main/components/Edit/Main/Tools.vue";

const editor = useEditor({
  content: '<p>点击这里进行编辑...</p>',
  extensions: [StarterKit],
})

const focusOnParagraph = () => {
  if (editor) {
    editor.value?.commands.focus(); // 将焦点设置到编辑器
  }
}

function getjosn(){
  console.log(editor.value?.getJSON())
}
</script>

<template>

  <!--编辑器 工具-->
  <Tools/>

  <div class="editor-content" @click="focusOnParagraph">
    <editor-content :editor="editor"/>
  </div>
</template>

<style scoped>
.editor-content {
  outline: none; /* 去掉选中时的黑框 */
  cursor: text; /* 鼠标悬停时显示文本光标 */
  border: 1px dashed rgba(255, 255, 255, 0); /* 添加边框以指示可编辑 */
  padding-left: 4px;
  transition: border-color 0.3s ease, background-color 0.3s ease; /* 添加过渡效果 */

  border-radius: 3px;
}

/* 鼠标悬停时的样式 */
.editor-content:hover {
  border-color: #ccc; /* 更改边框颜色以指示可编辑 */
  background-color: #f9f9f9; /* 背景颜色变化 */
}

/* 去掉文本选中时的轮廓 */
.editor-content *:focus {
  outline: none;
}

/* 去掉编辑器的默认文本选中样式 */
.editor-content p:focus {
  outline: none;
}
</style>
