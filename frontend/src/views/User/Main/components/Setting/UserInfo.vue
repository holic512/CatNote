<script setup lang="ts">
import {onMounted, ref} from 'vue';
import Button from 'primevue/button';
import {Edit} from '@element-plus/icons-vue';
import {AccountInfo, fetchAccountInfo} from "@/views/User/Main/components/Setting/service/userInfo.ts";

const Info = ref<AccountInfo>({});

onMounted(async () => {
  Info.value = await fetchAccountInfo();
})

</script>

<template>
  <h1 style="margin: 0">我的账号</h1>
  <el-divider class="divider"/>

  <div class="info-container">
    <!-- Avatar Section -->
    <div class="info-row">
      <el-text class="label" type="info">个人头像</el-text>
      <div class="avatar-container">
        <el-avatar size="large" :src="Info.avatar">
          {{ Info.username }}
        </el-avatar>
        <div class="avatar-actions">
          <Button label="上传新头像" severity="secondary" rounded class="upload-btn"/>
          <el-text size="small">支持JPG、PNG、GIF格式，小于2MB</el-text>
        </div>
      </div>
    </div>

    <!-- Username Section -->
    <div class="info-row">
      <el-text class="label" type="info">用户昵称</el-text>
      <el-text class="user-text">{{ Info.nickname }}</el-text>
      <el-link class="edit-link" :icon="Edit" type="primary">编辑</el-link>
    </div>

    <!-- User UID Section -->
    <div class="info-row">
      <el-text class="label" type="info">用户 Uid</el-text>
      <el-text class="user-text">{{ Info.uid }}</el-text>
    </div>

    <!-- Account Section -->
    <div class="info-row">
      <el-text class="label" type="info">账号</el-text>
      <el-text class="user-text">{{ Info.username }}</el-text>
    </div>

    <!-- Password Section -->
    <div class="info-row">
      <el-text class="label" type="info">密码</el-text>
      <el-text class="user-text">******</el-text>
      <el-link class="edit-link" :icon="Edit" type="primary">修改密码</el-link>
    </div>

    <!-- Email Section -->
    <div class="info-row">
      <el-text class="label" type="info">邮件地址</el-text>
      <el-text class="user-text">{{ Info.email }}</el-text>
      <el-link class="edit-link" :icon="Edit" type="primary">编辑</el-link>
    </div>

    <!-- Gender Section -->
    <div class="info-row">
      <el-text class="label" type="info">性别</el-text>
      <el-text class="user-text">{{ Info.gender }}</el-text>
      <el-link class="edit-link" :icon="Edit" type="primary">编辑</el-link>
    </div>

    <!-- Age Section -->
    <div class="info-row">
      <el-text class="label" type="info">年龄</el-text>
      <el-text class="user-text">{{ Info.age ? "" : "暂无"}}</el-text>
      <el-link class="edit-link" :icon="Edit" type="primary">编辑</el-link>
    </div>

    <!-- Contact Information Section -->
    <div class="info-row">
      <el-text class="label" type="info">联系方式</el-text>
      <el-text class="user-text">{{ Info.contactInfo ? "" : "暂无" }}</el-text>
      <el-link class="edit-link" :icon="Edit" type="primary">编辑</el-link>
    </div>

    <!-- Bio Section -->
    <div class="info-row">
      <el-text class="label" type="info">个人简介</el-text>
      <el-text class="user-text">{{ Info.bio? "" : "暂无" }}</el-text>
      <el-link class="edit-link" :icon="Edit" type="primary">编辑</el-link>
    </div>
  </div>
</template>

<style scoped>
.divider {
  margin: 8px 0 8px 0;
}

.info-container {
  padding: 8px;
}

.info-row {
  display: flex;
  align-items: center;
  margin-bottom: 18px;
}

.label {
  margin-right: 32px;
  width: 64px;
}

.user-text {
  margin-right: 8px;
}

/* 只有在悬停时才显示编辑链接 */
.edit-link {
  visibility: hidden; /* 默认隐藏 */
  opacity: 0; /* 默认透明 */
  transition: visibility 0s, opacity 0.2s ease-in-out; /* 平滑过渡 */
}

.info-row:hover .edit-link {
  visibility: visible; /* 悬停时可见 */
  opacity: 1; /* 悬停时不透明 */
}

.avatar-container {
  display: flex;
  align-items: center;
}

.avatar-actions {
  display: flex;
  flex-direction: column;
  margin-left: 16px;
}

.upload-btn {
  width: 128px;
  height: 28px;
  font-size: 14px;
}
</style>
