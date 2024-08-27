<template>
  <div class="login-container">
    <el-card class="login-form">
      <h1>{{ isRegister ? '注册' : '登录' }}</h1>
      <el-form @submit.prevent="isRegister ? handleRegister : handleLogin" label-width="80px">
        <div v-if="isRegister">
          <el-form-item label="用户名">
            <el-input v-model="registerUsername" placeholder="用户名" required class="el-input" />
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="registerEmail" placeholder="邮箱" required class="el-input" />
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="registerPassword" type="password" placeholder="密码" required class="el-input" />
          </el-form-item>
        </div>
        <div v-else>
          <div v-if="useEmail">
            <el-form-item label="邮箱">
              <el-input v-model="loginEmail" placeholder="邮箱" required class="el-input" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="sendVerificationCode" class="el-button">发送验证码</el-button>
            </el-form-item>
            <el-form-item label="验证码">
              <el-input v-model="verificationCode" placeholder="验证码" required class="el-input" />
            </el-form-item>
          </div>
          <div v-if="useAccount">
            <el-form-item label="用户名">
              <el-input v-model="loginUsername" placeholder="用户名" required class="el-input" />
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="loginPassword" type="password" placeholder="密码" required class="el-input" />
            </el-form-item>
          </div>
        </div>
        <el-form-item>
          <el-button type="primary" native-type="submit" class="el-button login-btn">{{ isRegister ? '注册' : '登录' }}</el-button>
        </el-form-item>
      </el-form>
      <div class="switch-link">
        <el-button type="text" @click="toggleForm" class="el-button">
          {{ isRegister ? '已有账号？登录' : '没有账号？注册' }}
        </el-button>
        <el-button v-if="!isRegister" type="text" @click="toggleLoginMethod" class="el-button">
          {{ useEmail ? '切换账号密码登录' : '切换邮箱验证码登录' }}
        </el-button>
      </div>
    </el-card>
  </div>
</template>



<script setup lang="ts">
import { ref } from 'vue';
import { login, register } from './authService.ts';

const isRegister = ref(false);
const useEmail = ref(true);
const useAccount = ref(false);

// 登录
const loginEmail = ref('');
const loginUsername = ref('');
const loginPassword = ref('');
const verificationCode = ref('');

// 注册
const registerUsername = ref('');
const registerEmail = ref('');
const registerPassword = ref('');

function handleLogin() {
  if (useEmail.value) {
    login(loginEmail.value, verificationCode.value, 'email');
  } else if (useAccount.value) {
    login(loginEmail.value, verificationCode.value, "email");
  }
}

function handleRegister() {
  register(registerUsername.value, registerEmail.value, registerPassword.value);
}

function useEmailCode() {
  useEmail.value = true;
  useAccount.value = false;
}

function useAccountPassword() {
  useEmail.value = false;
  useAccount.value = true;
}

function sendVerificationCode() {
  // 发送验证码逻辑
}

function toggleForm() {
  isRegister.value = !isRegister.value;
}

function toggleLoginMethod() {
  if (useEmail.value) {
    useAccountPassword();
  } else {
    useEmailCode();
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(135deg, #71b7e6, #9b59b6);
  padding: 20px;
  box-sizing: border-box;
}

.login-form {
  width: 100%;
  max-width: 400px;
  padding: 40px;
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
  text-align: center;
}

h2 {
  margin-bottom: 20px;
  color: #333;
  font-size: 24px;
}

.el-input {
  width: 100%;
  border-radius: 5px;
  transition: border-color 0.3s, box-shadow 0.3s;
}

.el-input:focus {
  border-color: #71b7e6;
  box-shadow: 0 0 5px rgba(113, 183, 230, 0.5);
}

.el-button {
  width: 100%;
  margin-top: 10px;
  border-radius: 5px;
  font-size: 16px;
  transition: background-color 0.3s, border-color 0.3s;
}

.switch-link {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.switch-link .el-button {
  font-size: 14px;
  color: #71b7e6;
}

.switch-link .el-button:hover {
  color: #5fa5d3;
}

.login-btn {
  margin-top: 40px;
}
</style>
