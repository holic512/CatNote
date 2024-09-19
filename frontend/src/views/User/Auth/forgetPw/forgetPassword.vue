<script setup lang="ts">
import { ref } from "vue";
import { ElMessage } from "element-plus";

import { useRouter } from "vue-router";

// 路由实例
const router = useRouter();

const email = ref<string>('');
const code = ref<string>('');
const newPassword = ref<string>('');
const codeSent = ref(false);
const agreedToTerms = ref(false); // 控制是否同意服务协议

// 协议同意处理
const validateTerms = () => {
  if (!agreedToTerms.value) {
    ElMessage('请同意服务协议');
    return false;
  }
  return true;
};

// 发送重置密码的验证码
const sendResetCode = async () => {
  if (!email.value) {
    ElMessage('请输入邮箱地址');
    return;
  }

  const status = await sendMail(email.value);
  const messages: { [key: number]: string } = {
    200: '发送成功',
    400: '邮箱格式不正确',
    404: '该邮箱不存在',
    408: '无法连接服务器'
  };
  ElMessage[status === 200 ? 'success' : 'info'](messages[status]);

  codeSent.value = true;
  setTimeout(() => (codeSent.value = false), 60000); // 模拟发送验证码并在60秒后重置按钮
};

// 重置密码逻辑
const resetPassword = async () => {
  if (!validateTerms()) {
    return;
  }

  if (!email.value || !code.value || !newPassword.value) {
    ElMessage('请填写完整信息');
    return;
  }

  const status = await verifyResetCode(email.value, code.value, newPassword.value);
  const messages: { [key: number]: string } = {
    200: '密码重置成功',
    400: '请输入正确的验证码和邮箱格式',
    401: '验证码错误',
    408: '无法连接服务器'
  };
  ElMessage[status === 200 ? 'success' : 'info'](messages[status]);

  if (status === 200) {
    router.push({ name: 'user-login' });
  }
};

</script>

<template>
  <div class="reset-container">
    <el-card class="reset-box">
      <h1 class="title">重置密码</h1>
      <el-form @submit.prevent="resetPassword">
        <div class="input-group">
          <label for="email">邮箱地址</label>
          <el-input type="text" id="email" v-model="email" placeholder="请输入邮箱地址" clearable></el-input>
        </div>

        <div class="input-group">
          <label for="code">验证码</label>
          <el-input type="text" id="code" v-model="code" placeholder="请输入验证码" clearable>
            <template #append>
              <el-button @click="sendResetCode" :disabled="codeSent">{{ codeSent ? '已发送' : '发送验证码' }}</el-button>
            </template>
          </el-input>
        </div>

        <div class="input-group">
          <label for="newPassword">新密码</label>
          <el-input type="password" id="newPassword" v-model="newPassword" placeholder="请输入新密码" clearable></el-input>
        </div>

        <el-checkbox v-model="agreedToTerms">我已阅读并同意<a href="#">服务协议</a></el-checkbox>

        <el-button type="primary" class="reset-button" :disabled="!agreedToTerms" @click="resetPassword">重置密码</el-button>
      </el-form>
    </el-card>
  </div>
</template>

<style scoped>
.reset-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 97vh;
  background-color: #f5f5f5;
}

.reset-box {
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

.reset-button {
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 4px;
  background-color: #333;
  color: #fff;
  font-size: 16px;
  cursor: pointer;
}

.reset-button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

el-checkbox {
  margin-bottom: 20px;
}
</style>
