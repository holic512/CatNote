<script setup lang="ts">
// 监听是否显示
import {useNoteCoverState} from "@/views/User/Main/components/Edit/Main/SetCover/paina/NoteCoverState";
import {onMounted, onUnmounted, watch} from "vue";
import {useCurrentNoteInfoStore} from "@/views/User/Main/components/Edit/Pinia/currentNoteInfo";
import {upNoteCover} from "@/views/User/Main/components/Edit/Main/SetCover/service/UpNoteCover";
import {useNoteTreeUpdate} from "@/views/User/Main/components/Sidebar/Pinia/isNoteTreeUpdated";

// NoteCoverState 是否显示的 pinia
const NoteCoverState = useNoteCoverState();

// 监听是否显示
watch(() => NoteCoverState.NoteCoverVis, () => {
  if (NoteCoverState.NoteCoverVis) {
    isFeatureDivClicked = true;
  }
})


// 用于判断是否点击了开启背景设置的按钮
let isFeatureDivClicked = false;

// 处理点击其他地方时关闭 div 的逻辑
function handleGlobalClick(event: MouseEvent) {
  const target = event.target as HTMLElement;

  // 如果点击了开启背景设置的按钮，不执行关闭操作
  if (isFeatureDivClicked) {
    isFeatureDivClicked = false;
    return;
  }

  if (!target.closest('.SetCoverContain') && NoteCoverState.NoteCoverVis) {
    NoteCoverState.NoNoteCover();
  }
}

onMounted(() => {
  // 在组件挂载时添加事件监听
  document.addEventListener('click', handleGlobalClick);
});

onUnmounted(() => {
  // 在组件卸载时移除事件监听
  document.removeEventListener('click', handleGlobalClick);
});

// 获取当前笔记 的 基础信息
const currentNoteInfo = useCurrentNoteInfoStore()

const upNoteCoverProxy = async (noteCover: any) => {
  if (currentNoteInfo.noteId != null) {

    const status = await upNoteCover(currentNoteInfo.noteId, noteCover);
    if (status == 200) {
      // 更新背景
      currentNoteInfo.cover = noteCover;

      // 刷新笔记树
      const isNoteTreeUpdated = useNoteTreeUpdate();
      isNoteTreeUpdated.UpdatedNoteTree();

      // 执行关闭
      NoteCoverState.NoNoteCover();
    }

  }
}

</script>

<template>
  <div class="SetCoverContain" v-if="NoteCoverState.NoteCoverVis">
    <div class="SetCoverHeader">
      <el-text style="margin-left: 8px" size="small">封面设置</el-text>
      <div class="SetCoverHeaderFlex"></div>
      <el-button size="small" text @click="upNoteCoverProxy(null)">移除背景</el-button>
    </div>
    <div class="SetCoverContent">
      <el-scrollbar height="290" class="SetCoverScrollbar">

        <div class="SetCoverImageContainer">
          <div class="SetCoverText">
            <el-text size="small" type="info">当前背景</el-text>
          </div>
          <el-image class="SetCoverImage"
                    :src="'/NoteCover/noteCover' + currentNoteInfo.cover + '.jpg'"
                    fit="cover"/>
        </div>

        <!--  颜色与渐变  -->
        <div class="SetCoverImageContainer">
          <div class="SetCoverText">
            <el-text size="small" type="info">颜色与渐变</el-text>
          </div>
          <div v-for="i in 14" :key="'color-' + i" @click="upNoteCoverProxy('1-'+ i.toString().padStart(3, '0')) ">
            <el-image class="SetCoverImage"
                      :src="'/NoteCover/noteCover1-' + i.toString().padStart(3, '0') + '.jpg'"
                      fit="cover"/>
          </div>
        </div>

        <!--  花纹  -->
        <div class="SetCoverImageContainer">
          <div class="SetCoverText">
            <el-text size="small">花纹</el-text>
          </div>
          <div v-for="i in 6" :key="'pattern-' + i" @click="upNoteCoverProxy('2-'+ i.toString().padStart(3, '0')) ">
            <el-image class="SetCoverImage"
                      :src="'/NoteCover/noteCover2-' + i.toString().padStart(3, '0') + '.jpg'"
                      fit="cover"/>
          </div>
        </div>
      </el-scrollbar>
    </div>
  </div>
</template>

<style scoped>
.SetCoverContain {
  position: absolute;
  width: 540px;
  height: 360px;
  background-color: white;
  top: 160px;
  left: 40vw;
  box-shadow: 10px 10px 15px rgba(0, 0, 0, 0.3);
  border-radius: 4px;
  border: #EDEDEC 1px solid;
}

.SetCoverHeader {
  height: 36px;
  border-bottom: #EDEDEC 1px solid;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 4px;
}

.SetCoverHeaderFlex {
  flex: 1;
}

.SetCoverContent {
  padding: 12px;
}

.SetCoverScrollbar {
  padding: 0 4px 4px 4px;
}

.SetCoverImageContainer {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
}

.SetCoverText {
  width: 100%;
}

.SetCoverImage {
  width: 120px;
  height: 60px;
  border-radius: 4px;
}
</style>
