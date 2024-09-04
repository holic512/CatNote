<script setup lang="ts">
import {ref} from "vue";
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";
import {initiateReg} from "../services/register.ts";
// 验证验证码页面
import RegisterVer from "./registerVer.vue";

// 路由实例
const router = useRouter();

// 账号参数
const username = ref<string>('');
const password = ref<string>('');
const confirmPassword = ref<string>('');
const email = ref<string>('');


// 控制服务协议是否同意
const agreedToTerms = ref(false);

// 协议同意处理
const validateTerms = () => {
  if (!agreedToTerms.value) {
    ElMessage('请同意服务协议');
    return false;
  }
  return true;
};


// 控制是否发送注册请求
const isVerificationStep = ref<boolean>(false);


// 注册按钮逻辑
const submitForm = async () => {
  if (!validateTerms()) {
    return;
  }
  if (!username.value || !password.value || !confirmPassword.value || !email.value) {
    ElMessage('请填写完整的注册信息');
    return;
  }

  if (password.value !== confirmPassword.value) {
    ElMessage('两次输入的密码不一致');
    return;
  }
  console.log(1);
  // 发送注册请求
  const status = await initiateReg(username.value, password.value, email.value);
  console.log(status);
  switch (status) {
    case 200:
      ElMessage.success('注册成功');
      // 跳转输入验证码页面
      isVerificationStep.value = true;
  }


};


</script>

<template>
  <div class="register-container" v-if="!isVerificationStep">
    <el-card class="register-box">
      <h1 class="title">用户注册</h1>
      <el-form @submit.prevent="submitForm">

        <!-- 用户名 -->
        <div class="input-group">
          <label for="username">用户名</label>
          <el-input type="text" id="username" v-model="username" placeholder="请输入用户名" clearable></el-input>
        </div>

        <!-- 邮箱 -->
        <div class="input-group">
          <label for="email">邮箱地址</label>
          <el-input type="text" id="email" v-model="email" placeholder="请输入邮箱地址" clearable></el-input>
        </div>

        <!-- 密码 -->
        <div class="input-group">
          <label for="password">密码</label>
          <el-input type="password" id="password" v-model="password" placeholder="请输入密码" clearable></el-input>
        </div>

        <!-- 确认密码 -->
        <div class="input-group">
          <label for="confirmPassword">确认密码</label>
          <el-input type="password" id="confirmPassword" v-model="confirmPassword" placeholder="请再次输入密码"
                    clearable></el-input>
        </div>

        <!-- 服务协议 -->
        <el-checkbox v-model="agreedToTerms">我已阅读并同意<a href="#">服务协议</a></el-checkbox>

        <!-- 注册按钮 -->
        <el-button type="primary" class="register-button" :disabled="!agreedToTerms" @click="submitForm">注册
        </el-button>

        <!-- 登录选项 -->
        <div class="register-options">
          <el-link type="info" @click="router.push({name:'user-login'})">已有账号？去登录</el-link>
        </div>

      </el-form>
    </el-card>
  </div>

  <RegisterVer v-else/>

</template>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 97vh;
  background-color: #f5f5f5;
}

.register-box {
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

.register-button {
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 4px;
  background-color: #333;
  color: #fff;
  font-size: 16px;
  cursor: pointer;
}

.register-button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.register-options {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
</style>
