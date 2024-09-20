<template>
  <div class="sidebar-header">
    <div class="user-info">
      <img :src="userAvatar" alt="User Avatar" class="avatar" />
      <span class="user-name">{{ userName }}的Notion</span>
    </div>
    <div class="actions">
      <Button icon="pi pi-search" label="搜索" class="action-button" @click="openSearchDialog" />
      <Button icon="pi pi-home" label="主页" class="action-button" @click="goToHome" />
      <Button icon="pi pi-envelope" label="收件箱" class="action-button" />
    </div>
  </div>
</template>

<script>
import Button from 'primevue/button';
export default {
  props: {
    userName: {
      type: String,
      default: '用户名',
    },
    userAvatar: {
      type: String,
      default: 'https://via.placeholder.com/50', // 占位符头像图片
    },
  },
  components: {
    Button,
  },
  setup() {
    const goToHome = () => {
      console.log('跳转到主页');
    };

    const openSearchDialog = () => {
      console.log('打开搜索对话框');
    };

    return {
      goToHome,
      openSearchDialog,
    };
  },
};
</script>

<style scoped>
.sidebar-header {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 16px;
  background-color: #f8f9fa;
  border-bottom: 1px solid #e0e0e0;
}

.user-info {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-bottom: 10px;
}

.avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin-right: 8px;
}

.user-name {
  font-weight: bold;
  font-size: 14px;
  color: #333;
}

.actions {
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: flex-start;
}

.action-button {
  width: 100%;
  justify-content: flex-start; /* 图标和文字左对齐 */
  background: transparent;
  color: gray;
  border: none;
  padding: 8px;
}
</style>
