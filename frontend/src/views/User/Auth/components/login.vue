<script setup lang="ts">
import {ref} from "vue";
import {ElMessage} from "element-plus";


// 导入登录函数
import pwLogin from "../services/pwLogin.ts"
import {sendMail, verifyLoginCode} from "../services/emLogin.ts";
import {useRouter} from "vue-router";

// 路由实例
const router = useRouter();

const loginType = ref<string>('password'); // 控制是密码登录还是验证码登录
const username = ref<string>('');
const password = ref<string>('');

const agreedToTerms = ref(false); // 控制是否同意服务协议

// 不同条件选择框
const switchLoginType = () => {
  loginType.value = loginType.value === 'password' ? 'code' : 'password';
};

// 协议同意处理
const validateTerms = () => {
  if (!agreedToTerms.value) {
    ElMessage('请同意服务协议');
    return false;
  }
  return true;
};

// 登录按钮逻辑
const submitForm = async () => {
  if (!validateTerms()) {
    return;
  }

  if (loginType.value === 'password') {
    await handlePasswordLogin();
  } else {
    await handleCodeLogin();
  }
};


// 账号密码登录逻辑
const handlePasswordLogin = async () => {
  if (!username.value || !password.value) {
    return;
  }

  const status = await pwLogin(username.value, password.value);
  const messages: { [key: number]: string } = {
    200: '登陆成功',
    401: '密码错误',
    404: '没有找到此账号',
    408: '无法连接服务器'
  };

  ElMessage[status === 200 ? 'success' : 'info'](messages[status]);
};

const email = ref('');
const code = ref('');
const codeSent = ref(false);

// 发送邮箱逻辑
const sendEmCode = async () => {
  if (!email.value) {
    ElMessage('请输入邮箱地址');
    return;
  }

  const status = await sendMail(email.value)
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

// 邮箱登录逻辑
const handleCodeLogin = async () => {
  console.log('邮箱地址:', email.value);
  console.log('验证码:', code.value);
  const status = await verifyLoginCode(email.value, code.value)
  const messages: { [key: number]: string } = {
    200: '登录成功',
    400: '请输入正确的验证码和邮箱格式',
    401: '验证失败',
    408: '无法连接服务器'
  };
  ElMessage[status === 200 ? 'success' : 'info'](messages[status]);
};


</script>
<template>
  <div class="login-container">
    <el-card class="login-box">
      <h1 class="title">用户登录</h1>
      <el-form @submit.prevent="submitForm">
        <!-- 密码登录  -->
        <div v-if="loginType === 'password'">
          <div class="input-group">
            <label for="username">用户名</label>
            <el-input type="text" id="username" v-model="username" placeholder="请输入用户名" clearable></el-input>
          </div>

          <div class="input-group">
            <label for="password">密码</label>
            <el-input type="password" id="password" v-model="password" placeholder="请输入密码" clearable></el-input>
          </div>
        </div>
        <!--   邮箱登录     -->
        <div v-if="loginType === 'code'">
          <div class="input-group">
            <label for="email">邮箱地址</label>
            <el-input type="text" id="email" v-model="email" placeholder="请输入邮箱地址" clearable></el-input>
          </div>

          <div class="input-group">
            <label for="code">验证码</label>
            <el-input type="text" id="code" v-model="code" placeholder="请输入验证码" clearable>
              <template #append>
                <el-button @click="sendEmCode" :disabled="codeSent">{{ codeSent ? '已发送' : '发送验证码' }}</el-button>
              </template>
            </el-input>
          </div>
        </div>

        <el-checkbox v-model="agreedToTerms">我已阅读并同意<a href="#">服务协议</a></el-checkbox>

        <el-button type="primary" class="login-button" :disabled="!agreedToTerms" @click="submitForm">登录</el-button>

        <div class="login-options">
          <el-link type="primary" @click="switchLoginType">
            {{ loginType === 'password' ? '验证码登录' : '密码登录' }}
          </el-link>
          <div class="more-options" v-if="loginType === 'password'">
            <el-link type="info">忘记密码</el-link>
            <el-link type="info" @click="router.push({name:'user-register'})">创建账号</el-link>
          </div>

          <el-link type="info" v-if="loginType === 'code'"
                   @click="router.push({name:'user-register'})">创建账号
          </el-link>
        </div>
      </el-form>
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
}

.login-button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.login-options {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.more-options {
  display: flex;
  gap: 10px;
}

el-checkbox {
  margin-bottom: 20px;
}
</style>
