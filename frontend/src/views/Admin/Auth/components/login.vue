<script setup lang="ts">
import {ref} from 'vue';
import {ElMessage} from 'element-plus';
import {login, verCode} from "../services/login.ts";

import InputOtp from 'primevue/inputotp';
import {useRouter} from 'vue-router';

// 路由实例
const router = useRouter();

// 一次登录,二次登录 状态区分标识符
const logView = ref(true);

// 账号密码 登录逻辑
const username = ref<string>('');
const password = ref<string>('');

const sendLogin = async () => {
  if (!username.value || !password.value) {
    ElMessage.error('用户名和密码不能为空');
    return;
  }

  const status = await login(username.value, password.value);
  const messages: { [key: number]: string } = {
    200: '登录成功,请进行二次验证',
    401: '密码错误',
    404: '没有找到此账号',
    500: '无法连接服务器'
  };
  ElMessage[status === 200 ? 'success' : 'error'](messages[status]);
  // 登录请求成功 进行二次验证
  if (status === 200) {
    logView.value = false
  }
};

// 二次邮箱验证逻辑
const codeValue = ref<string>('');
const verLogin = async () => {
  if (!codeValue.value || codeValue.value.length != 6) {
    return
  }

  const status = await verCode(codeValue.value)
  const messages: { [key: number]: string } = {
    200: '登录成功',
    401: '验证码错误错误',
    404: '没有找到此请求',
    400: 'json解析错误',
    500: '无法连接服务器'
  };
  ElMessage[status === 200 ? 'success' : 'error'](messages[status]);

  // 登录成功跳转其他页面
  if (status === 200) {
    await router.push("main")
  }
}
</script>

<template>
  <div class="login-container" v-if="logView">
    <el-card class="login-box">
      <h1 class="title">管理员登录</h1>
      <el-form>
        <div class="input-group">
          <label for="username">用户名</label>
          <el-input type="text" id="username" v-model="username" placeholder="请输入用户名" clearable></el-input>
        </div>

        <div class="input-group">
          <label for="password">密码</label>
          <el-input type="password" id="password" v-model="password" placeholder="请输入密码" clearable></el-input>
        </div>

        <el-button type="primary" class="login-button" @click="sendLogin">登录</el-button>
      </el-form>
    </el-card>
  </div>

  <!--  二次验证页面  -->
  <div class="login-container" v-else>
    <el-card class="login-box">
      <h1 class="verTitle">管理员登录验证</h1>
      <p class="description">请输入您收到的 6 位数字验证码</p>
      <InputOtp v-model="codeValue" integerOnly :length="6"/>
      <el-button type="primary" class="login-button" @click="verLogin">登录</el-button>

    </el-card>
  </div>

</template>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
}

.login-box {
  width: 400px;
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
  font-weight: 300;
}

.input-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 8px;
  color: #666;
  font-size: 14px;
}

.login-button {
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 4px;
  background-color: #333;
  color: #fff;
  font-size: 16px;
  cursor: pointer;
  margin-top: 20px;
}

.login-button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

/* 描述文字样式 */
.description {
  text-align: center; /* 文字居中 */
  margin-bottom: 20px; /* 底部外边距 */
  color: #666; /* 文字颜色 */
  font-size: 14px; /* 字体大小 */
  font-weight: 600
}

.verTitle {
  margin-bottom: 24px; /* 底部外边距 */
  color: #333; /* 文字颜色 */
  text-align: center; /* 文字居中 */
  font-size: 30px; /* 字体大小 */
  font-weight: 800; /* 字体粗细 */
}
</style>
