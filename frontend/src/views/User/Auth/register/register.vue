<script setup lang="ts">
import {ref, reactive} from "vue";
import {ElMessage, FormInstance, FormRules} from "element-plus";
import {initiateReg, VerReg} from "./services/register.ts";
import InputOtp from "primevue/inputotp";
import Dialog from "primevue/dialog";
import UserAgreement from "../components/userAgreement.vue";
import {useRouter} from "vue-router";

// 路由实例
const router = useRouter();

// 表单模型
const form = ref({
  username: "",
  password: "",
  confirmPassword: "",
  email: "",
  status: "ACTIVE"
});

// 验证规则
const rules = reactive<FormRules>({
  username: [
    {required: true, message: "请输入用户名", trigger: "blur"},
    {min: 6, max: 12, message: "用户名长度应在 6 到 12 个字符之间", trigger: "blur"},
    {
      validator: (rule, value, callback) => {
        const noChineseRegEx = /^[A-Za-z0-9_]+$/; // 匹配不含中文的字符串
        if (!noChineseRegEx.test(value)) {
          callback(new Error("用户名只能包含大小写英文、数字以及_"));
        } else {
          callback();
        }
      }, trigger: "blur"
    }
  ],
  password: [
    {required: true, message: "请输入密码", trigger: "blur"},
    {min: 6, message: "密码长度应不小于 6 位", trigger: "blur"}
  ],
  confirmPassword: [
    {required: true, message: "请确认密码", trigger: "blur"},
    {
      validator: (rule, value, callback) => {
        if (value !== form.value.password) {
          callback(new Error("两次输入密码不一致"));
        } else {
          callback();
        }
      }, trigger: "blur"
    }
  ],
  email: [
    {required: true, message: "请输入邮箱地址", trigger: "blur"},
    {type: "email", message: "请输入正确的邮箱地址", trigger: "change"}
  ]
});

// 控制服务协议是否同意
const agreedToTerms = ref(false);
const validateTerms = () => {
  if (!agreedToTerms.value) {
    ElMessage('请同意服务协议');
    return false;
  }
  return true;
};

// 控制是否发送注册请求
const isVerificationStep = ref<boolean>(true);

// 表单实例引用
const formEl = ref<FormInstance>();

// 注册按钮逻辑
const submitForm = async () => {
  // 验证服务协议
  if (!validateTerms()) {
    return;
  }

  // 验证表单
  if (formEl.value) {
    try {
      await formEl.value.validate();
      if (!form.value.username || !form.value.password || !form.value.confirmPassword || !form.value.email) {
        ElMessage('请填写完整的注册信息');
        return;
      }

      if (form.value.password !== form.value.confirmPassword) {
        ElMessage('两次输入的密码不一致');
        return;
      }

      // 发送注册请求
      const status = await initiateReg(form.value.username, form.value.password, form.value.email);
      switch (status) {
        case 200:
          ElMessage.success('注册成功');
          // 跳转输入验证码页面
          isVerificationStep.value = false;
          break;
        default:
          ElMessage.error('无法连接服务器');
          break;
      }
    } catch {
      ElMessage.warning("请填写完整用户信息");
    }
  }
};

// 邮箱验证流程
const codeValue = ref<string>('');
const verR = async () => {
  if (!codeValue.value || codeValue.value.length !== 6) {
    ElMessage.warning('验证码长度应为6位');
    return;
  }

  const status = await VerReg(codeValue.value);
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
    await router.push("login");
  }
}

// restaurants 用于实现 邮箱补全
const restaurants = [
  {value: '@qq.com'},
  {value: '@163.com'},
  {value: '@126.com'},
  {value: '@139.com'},
  {value: '@sina.com'},
  {value: '@gmail.com'},

];

// fetchSuggestions 用于 邮箱自动填充规则
const fetchSuggestions = (queryString: string, cb: any) => {
  if (queryString === '') {
    cb([])
    return
  }
  const hasAtSymbol = /@/.test(queryString);
  if (hasAtSymbol) {
    cb([])
    return
  }
  const suggestions = restaurants.map(item => {
        return {...item, value: queryString + item.value}
      }
  )
  cb(suggestions)
}

// 用户协议开关
const addUserVisible = ref(false);
</script>

<template>
  <div class="register-container" v-if="isVerificationStep">
    <el-card class="register-box">
      <h1 class="title">用户注册</h1>
      <el-form :model="form" :rules="rules" ref="formEl" label-width="auto" class="form-container"
               label-position="left">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" class="input" placeholder="请输入用户名"/>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" class="input" show-password placeholder="请输入密码"/>
        </el-form-item>

        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input v-model="form.confirmPassword" class="input" show-password placeholder="请确认密码"/>
        </el-form-item>

        <el-form-item label="邮箱地址" prop="email">
          <el-autocomplete
              v-model="form.email"
              :fetch-suggestions="fetchSuggestions"
              placeholder="请输入邮箱地址"
              style="width: 100%"
          />
        </el-form-item>

        <el-form-item>
          <el-checkbox v-model="agreedToTerms">我已阅读并同意
            <a href="#" @click="addUserVisible = true">服务协议</a>
          </el-checkbox>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" class="register-button" :disabled="!agreedToTerms" @click="submitForm">注册
          </el-button>
        </el-form-item>

        <div class="register-options">
          <el-link type="info" @click="router.push({ name: 'user-login' })">已有账号？去登录</el-link>
        </div>
      </el-form>
    </el-card>
  </div>

  <div class="login-container" v-else>
    <el-card class="login-box">
      <h1 class="verTitle">账号注册验证</h1>
      <p class="description">请输入您收到的 6 位数字验证码</p>
      <InputOtp v-model="codeValue" integerOnly :length="6"/>
      <el-button type="primary" class="login-button" @click="verR">注册</el-button>
    </el-card>
  </div>

  <Dialog v-model:visible="addUserVisible" header="CatNote 云笔记平台用户协议" :draggable="false" modal
          :style="{ width: '850px' }"
          :pt="{
            header: { style: { paddingBottom: '10px', paddingTop: '10px' } },
            content: { style: { borderTop: '1px solid #E2E8F0' } },
          }"
  >
    <UserAgreement/>
  </Dialog>
</template>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
}

.register-box {
  width: 400px;
  padding: 15px;
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

.verTitle {
  margin-bottom: 24px;
  color: #333;
  text-align: center;
  font-size: 30px;
  font-weight: 800;
}

/* 描述文字样式 */
.description {
  text-align: center; /* 文字居中 */
  margin-bottom: 20px; /* 底部外边距 */
  color: #666; /* 文字颜色 */
  font-size: 14px; /* 字体大小 */
  font-weight: 600
}

.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
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

.verTitle {
  margin-bottom: 24px; /* 底部外边距 */
  color: #333; /* 文字颜色 */
  text-align: center; /* 文字居中 */
  font-size: 30px; /* 字体大小 */
  font-weight: 800; /* 字体粗细 */
}
</style>