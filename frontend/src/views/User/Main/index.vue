<script setup lang="ts">
import Sidebar from "./components/Sidebar/Sidebar.vue"

import {ref, onMounted, onBeforeUnmount} from 'vue';

// 控制 Tooltip 显示与隐藏
const showTooltip = ref(false);

// 记录鼠标悬浮时的初始坐标
const fixedMouseX = ref(0);
const fixedMouseY = ref(0);

// 控制是否处于拖动状态
const isDragging = ref(false);

// 左侧面板的宽度
const panel1Width = ref(250);

// 记录是否锁定 Tooltip 位置
const positionLocked = ref(false);

// 鼠标按下时启动拖动
const onMouseDown = () => {
  isDragging.value = true;
  positionLocked.value = true;

  document.body.style.cursor = 'grab'; // 更改光标为抓取样式
};

// 鼠标悬浮在分割线上时显示 Tooltip
const onMouseEnter = (event: MouseEvent) => {
  if (isDragging.value) return;
  fixedMouseX.value = panel1Width.value + 4;
  fixedMouseY.value = event.pageY;
  showTooltip.value = true;
};

// 鼠标移出分割线时隐藏 Tooltip
const onMouseLeave = () => {
  showTooltip.value = false;
};

// 拖动过程中实时调整面板宽度
const onMouseMove = (event: MouseEvent) => {
  if (isDragging.value) {
    const newWidth = panel1Width.value + (event.pageX - fixedMouseX.value);
    if (newWidth >= 250 && newWidth <= 400) {
      panel1Width.value = newWidth;
      fixedMouseX.value = event.pageX; // 更新鼠标位置
    }
  }
};

// 鼠标抬起时停止拖动
const onMouseUp = () => {
  isDragging.value = false;
  showTooltip.value = false;
  document.body.style.cursor = 'default'; // 恢复光标样式
};

// 绑定和移除事件监听
onMounted(() => {
  window.addEventListener('mousemove', onMouseMove);
  window.addEventListener('mouseup', onMouseUp);
});

onBeforeUnmount(() => {
  window.removeEventListener('mousemove', onMouseMove);
  window.removeEventListener('mouseup', onMouseUp);
});
</script>

<template>
  <div class="splitter-container">
    <!-- 左侧面板 -->
    <div class="panel1" :style="{ width: panel1Width + 'px' }">
      <div style="height: 100vh; background-color: #f7f7f5;">

        <Sidebar/>

      </div>
    </div>

    <!-- 分割线 -->
    <div class="gutter"
         @mousedown="onMouseDown"
         @mouseenter="onMouseEnter"
         @mouseleave="onMouseLeave"
    ></div>

    <!-- 右侧面板 -->
    <div class="panel2">
      <div style="height: 100vh; background-color: #fff;">Panel 2</div>
    </div>

    <!-- Tooltip，位置锁定在鼠标初次进入的位置 -->
    <div
        v-if="showTooltip"
        class="tooltip"
        :style="{ left: fixedMouseX + 10 + 'px', top: fixedMouseY + 'px' }">
      <div class="tooltip-row">
        <span>关闭</span>
        <span class="light-text">点击</span>
      </div>
      <div class="tooltip-row">
        <span>调整大小</span>
        <span class="light-text">拖动</span>
      </div>
    </div>

  </div>
</template>

<style scoped>
.splitter-container {
  display: flex;
  height: 100vh;
}

/* 左右面板的样式 */
.panel1, .panel2 {
  height: 100vh;
}

/* 分割线的样式 */
.gutter {

  width: 2px;
  background-color: #F1F1EF;
  cursor: ew-resize;
  position: relative;
  transition: background-color 0.3s ease;
}

.gutter:hover {
  background-color: #ddd;
}

.gutter:active {
  background-color: #ddd;
}

/* 使用伪元素扩展选中范围 */
.gutter::before,
.gutter::after {
  content: '';
  position: absolute;
  width: 8px; /* 每侧扩展 4px */
  height: 100%; /* 高度填满 */
  background-color: transparent; /* 背景透明 */
}

/* 左侧扩展 */
.gutter::before {
  left: -4px; /* 向左扩展 2px */
}

/* 右侧扩展 */
.gutter::after {
  right: -4px; /* 向右扩展 2px */
}

.tooltip {
  position: absolute;
  padding: 6px 12px;
  background-color: #333;
  color: #fff;
  border-radius: 4px;
  font-size: 13px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
  transition: opacity 0.3s ease;
  z-index: 100;
  pointer-events: none;
  opacity: 0.9; /* 增加透明度使效果更柔和 */
}

/* 每一行的样式 */
.tooltip-row {
  display: flex;
  justify-content: space-between; /* 左右对齐 */
  width: 100%; /* 宽度占满 */
}

.light-text {
  color: #bbb; /* 设置浅色字体 */
  margin-left: 4px;
}
</style>
