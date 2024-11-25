<script setup lang="ts">

// 获取编辑器实例
import {BubbleMenu} from '@tiptap/vue-3'
import Explanation from "../../../../../../../icon/Explanation.vue";
import BoldText from "@/views/User/Main/components/Edit/Main/BubbleMenu/components/BoldText.vue";
import ItalicText from "@/views/User/Main/components/Edit/Main/BubbleMenu/components/ItalicText.vue";
import Underline from "@/views/User/Main/components/Edit/Main/BubbleMenu/components/Underline.vue";
import Strikethrough from "@/views/User/Main/components/Edit/Main/BubbleMenu/components/Strikethrough.vue";
import TextColor from "@/views/User/Main/components/Edit/Main/BubbleMenu/components/TextColor.vue";
import AI from "@/icon/AI.vue";
import Review from "@/icon/Review.vue";
import SetText from "@/views/User/Main/components/Edit/Main/BubbleMenu/SetText/SetText.vue";


const editor: any = defineModel()

</script>

<template>


  <bubble-menu
      :editor="editor"
      :tippy-options="{ duration: 100 }"
      v-if="editor"
  >
    <div class="bubbleBox">

      <el-button text class="buttonH" style="margin: 0 !important; padding-left: 4px;padding-right: 8px">
        <el-icon size="20" color="#000000">
          <Explanation/>
        </el-icon>
        <el-text style="color: #353535">
          解释
        </el-text>

      </el-button>

      <el-button class="buttonH" text style="margin: 0 !important; padding-left: 6px;padding-right: 10px">
        <el-icon size="20" color="#000000">
          <AI/>
        </el-icon>
        <el-text style="color: #353535">
          Ai
        </el-text>
      </el-button>

      <el-divider direction="vertical"/>

      <el-button class="buttonH" text style="margin: 0 !important; padding-left: 6px;padding-right: 8px">
        <el-icon size="18" color="#000000">
          <Review/>
        </el-icon>
        <el-text style="color: #353535">
          评论
        </el-text>
      </el-button>

      <el-divider direction="vertical"/>


      <SetText v-model="editor"/>

      <BoldText v-model="editor"/>

      <ItalicText v-model="editor"/>

      <Underline v-model="editor"/>

      <Strikethrough v-model="editor"/>

      <!--      代码-->

      <TextColor v-model="editor"/>

    </div>
  </bubble-menu>
</template>

<style scoped>
.bubbleBox {
  width: 450px; /* 占据父容器宽度 */
  display: flex;
  flex-wrap: nowrap; /* 不换行 */
  justify-content: space-between; /* 如果内容多，让它们平均分布 */
  align-items: center;
  background-color: white; /* 白底 */
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2); /* 更深的阴影 */
  border-radius: 4px; /* 圆角 */
  padding: 2px; /* 内边距 */
}

.buttonH {
  height: 28px;
}

.button {
  width: 28px;
}

</style>