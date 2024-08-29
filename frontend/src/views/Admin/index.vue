<script setup lang="ts">
import { ref, onMounted } from 'vue';

// 响应式变量
const username = ref<string>('');
const password = ref<string>('');

// 表单提交函数
const submitForm = () => {
};

// 输入框事件监听
const setupInputListeners = () => {
  const phoneInput = document.querySelector('.PhoneNumber') as HTMLInputElement;
  const passwordInput = document.querySelector('.InputPasswd') as HTMLInputElement;

  if (phoneInput) {
    phoneInput.addEventListener('focus', () => {
      if (phoneInput.value === '请输入用户名') {
        phoneInput.value = '';
      }
    });
    phoneInput.addEventListener('blur', () => {
      if (phoneInput.value === '') {
        phoneInput.value = '请输入用户名';
      }
    });
  }

  if (passwordInput) {
    passwordInput.addEventListener('focus', () => {
      if (passwordInput.value === '请输入密码') {
        passwordInput.value = '';
      }
    });
    passwordInput.addEventListener('blur', () => {
      if (passwordInput.value === '') {
        passwordInput.value = '请输入密码';
      }
    });
  }
};
// 组件挂载后设置输入框事件监听
onMounted(() => {
  setupInputListeners();
});

</script>

<template>
  <el-container class="container">
    <el-card class="login-box">
      <h1 class="title">管理员登录</h1>
      <el-form @submit.prevent="submitForm">
        <el-form-item class="form-item">
          <label for="username">用户名</label>
          <el-input
              id="username"
              v-model="username"
              placeholder="请输入用户名"
              clearable
              class="input-field"></el-input>
        </el-form-item>

        <el-form-item class="form-item">
          <label for="password">密码</label>
          <el-input
              id="password"
              type="password"
              v-model="password"
              placeholder="请输入密码"
              clearable
              class="input-field"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button
              type="primary"
              class="logBtn"
              @click="submitForm">登录</el-button>
        </el-form-item>

        <el-form-item>
          <router-link to="" class="FreeRegister">注册</router-link>
        </el-form-item>
      </el-form>
    </el-card>
  </el-container>
</template>


<style scoped>
/* 确保输入框宽度填满父容器 */
.input-field {
  width: 100%;
}

.logBtn {
  width: 100%;
  border-radius: 5px;
  border: 0;
  background-color: aqua;
  font-size: 20px;
  color: white;
  height: 40px;
  cursor: pointer;
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: rgba(125, 125, 125, 0.1);
  padding: 20px;
  box-sizing: border-box;
}

.login-box {
  width: 320px;
  padding: 40px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.title {
  margin-bottom: 24px;
  color: #333;
  text-align: center;
  font-size: 24px;
  font-weight: 300; /* 标题的字体粗细 */
}

.el-form-item {
  margin-bottom: 20px;
  display: flex;
  flex-direction: column;
  align-items: stretch; /* 确保子元素填满父容器 */
}

.form-item {
  display: flex;
  align-items: center;
  gap: 10px; /* 标签与输入框之间的间距 */
}

.form-item label {
  width: 100px; /* 标签宽度，可以根据需要调整 */
  font-weight: 300; /* 与标题一致的字体粗细 */
  margin-right: 10px; /* 标签与输入框之间的间距 */
}

.FreeRegister {
  display: block;
  font-weight: 300;
  text-align: center;
  color: black;
  text-decoration: none;
  margin-top: 10px;
}
</style>

