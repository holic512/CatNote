<!--  用于 编辑内容 的页头  -->
<script setup lang="ts">

import SaveNote from "@/views/User/Main/components/Edit/PageHeader/components/SaveNote/SaveNote.vue";
import SaveNoteState from "@/views/User/Main/components/Edit/PageHeader/components/SaveNoteState/SaveNoteState.vue";
import {useCurrentNoteInfoStore} from "@/views/User/Main/components/Edit/Pinia/currentNoteInfo.ts";

const editor = defineModel()

// 获取当前笔记 的 基础信息
const currentNoteInfo = useCurrentNoteInfoStore()

</script>

<template>
  <el-row justify="space-between">

    <!--    文件路径头   -->
    <el-col :span="8" class="el-col">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item v-for="(noteLocation) in currentNoteInfo.noteLocation">
          <el-text tag="b">{{ noteLocation }}</el-text>
        </el-breadcrumb-item>
        <el-breadcrumb-item>
          <el-text tag="b">{{ currentNoteInfo.noteName }}</el-text>
        </el-breadcrumb-item>
      </el-breadcrumb>

      <!--  文件保存 状态-->
      <SaveNoteState/>
    </el-col>

    <el-col :span="16" class="el-col el-col-right">

      <SaveNote v-model="editor"/>

      <el-tooltip
          class="box-item"
          effect="dark"
          content=" 分享 "
          :show-after="500"
          placement="bottom"
      >

        <el-button text class="button">
          <el-icon color="#000000" size="18">
            <Share/>
          </el-icon>
        </el-button>

      </el-tooltip>

      <el-tooltip
          class="box-item"
          effect="dark"
          content=" 评论 "
          :show-after="500"
          placement="bottom"
      >

        <el-button text class="button"> <!--评论-->
          <el-icon color="#000000" size="18">
            <ChatLineSquare/>
          </el-icon>
        </el-button>

      </el-tooltip>

      <el-tooltip
          class="box-item"
          effect="dark"
          content=" 历史版本 "
          :show-after="500"
          placement="bottom"
      >

        <el-button text class="button"> <!--历史版本-->
          <el-icon color="#000000" size="18">
            <Clock/>
          </el-icon>
        </el-button>

      </el-tooltip>

      <el-tooltip
          class="box-item"
          effect="dark"
          content=" 收藏 "
          :show-after="500"
          placement="bottom"
      >

        <el-button text class="button">  <!--收藏-->
          <el-icon color="#000000" size="18">
            <Star/>
          </el-icon>
        </el-button>

      </el-tooltip>

      <el-tooltip
          class="box-item"
          effect="dark"
          content=" 更多 "
          :show-after="500"
          placement="bottom"
      >

        <el-button text class="button"> <!--更多-->
          <el-icon color="#000000" size="18">
            <MoreFilled/>
          </el-icon>
        </el-button>

      </el-tooltip>

    </el-col>
  </el-row>
</template>

<style scoped>
.el-col {
  display: flex;
  align-items: center;
  height: 48px;
}

.el-col-right {
  justify-content: flex-end;
}

.button {
  width: 30px;
  height: 30px;
}

</style>