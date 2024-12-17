<script setup lang="ts">
import {computed, ref, onMounted, onBeforeUnmount} from "vue";

import '../css/editTool-button.css'

// 引入表情选择器相关配置
import EmojiPicker, {EmojiExt} from "vue3-emoji-picker";
import "vue3-emoji-picker/css";

const editor: any = defineModel();

// 表情选择器分组名称
const iln = {
  smileys_people: "笑脸与人物",
  animals_nature: "动物与自然",
  food_drink: "食物与饮品",
  activities: "活动",
  travel_places: "旅行与地点",
  objects: "物品",
  symbols: "符号",
  flags: "旗帜",
};

// 表情选择器的显示与位置控制
const showEmojiPicker = ref(false);
const buttonRef = ref<HTMLElement | null>(null);
const emojiPosition = ref({x: 0, y: 0});

const toggleEmojiPicker = () => {
  const buttonElement = (buttonRef.value as any)?.$el
  if (buttonElement) {
    const rect = buttonElement.getBoundingClientRect();
    emojiPosition.value = {
      x: rect.left - 140, // 按钮左侧位置
      y: rect.bottom + 10, // 按钮底部向下 20px
    };
    showEmojiPicker.value = !showEmojiPicker.value;
  } else {
    console.warn("buttonRef is not a valid DOM element");
  }
};

// 表情选择回调
const onSelectEmoji = (emoji: EmojiExt) => {
  editor.value?.chain().focus().insertContent(emoji.i).run(); // 插入表情
  showEmojiPicker.value = false; // 隐藏表情选择器
};

const emojiPickerRef = ref<HTMLElement | null>(null);

// 监听点击事件，点击表情选择器外部时关闭它
const handleClickOutside = (event: MouseEvent) => {
  const buttonElement = (buttonRef.value as any)?.$el
  if (
      !buttonElement.contains(event.target as Node)
  ) {
    showEmojiPicker.value = false; // 隐藏表情选择器
  }
};

// 注册和注销全局点击事件监听
onMounted(() => {
  document.addEventListener("click", handleClickOutside);
});

onBeforeUnmount(() => {
  document.removeEventListener("click", handleClickOutside);
});

</script>

<template>

  <el-tooltip
      class="box-item"
      effect="dark"
      content=" 表情选择器 "
      :show-after="500"
      placement="bottom"
  >
    <!-- 按钮 -->
    <el-button text class="tool-button tool-emoji-button" ref="buttonRef" @click="toggleEmojiPicker">
      😀
    </el-button>
  </el-tooltip>

  <!-- 表情选择器 -->
  <div v-show="showEmojiPicker"
       :style="{ position: 'absolute', top: emojiPosition.y + 'px', left: emojiPosition.x + 'px' }"
       class="tool-emoji-box"
  >
    <div style="border-bottom: 1px #EDEDEC solid;padding: 4px;display: flex;  height: 36px;">
      <el-text style="margin-left: 8px" size="small">插入表情</el-text>
    </div>

    <EmojiPicker
        ref="emojiPickerRef"
        @select="onSelectEmoji"
        :group-names="iln"
        class="insert-emoji"
        :disable-skin-tones="true"
        :hide-group-icons="true"
        :hide-search="true"

    />
  </div>

</template>

<style scoped>
.tool-emoji-box {
  z-index: 1000; /* 确保表情选择器在其他内容之上 */
  background-color: white;
  border-radius: 4px;
  width: 290px;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);

  border: #EDEDEC 1px solid;
}

.insert-emoji {
  border-radius: 8px;
  box-shadow: 0 0;
}
.tool-emoji-button{
  font-size: 18px;
  padding-top: 10px;
}
</style>
