<template>
  <div class="verification-container">
    <el-card class="verification-box">
      <h1 class="title">验证码验证</h1>
      <p class="description">请输入您收到的 6 位数字验证码</p>
      <div class="verification-inputs">
        <input
            v-for="(code, index) in verificationCodes"
            :key="index"
            v-model="verificationCodes[index]"
            @input="handleInput(index, $event)"
            @keydown="handleKeyDown(index, $event)"
            maxlength="1"
            class="verification-input"
        />
      </div>
      <el-button type="primary" class="verify-button" @click="submitVerificationCode">验证</el-button>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import {ref, defineEmits, nextTick} from 'vue';

const emits = defineEmits(['emailCode']);
const verificationCodes = ref(['', '', '', '', '', '']);

const handleInput = (index, event) => {
  const value = event.target.value;
  verificationCodes.value[index] = value;

  // 判断是否输入完成
  if (verificationCodes.value.join('').length === 6) {
    emits('emailCode', verificationCodes.value.join(''));
  }

  // 自动跳到下一个输入框
  if (value && index < verificationCodes.value.length - 1) {
    const nextInput = event.target.nextElementSibling;
    if (nextInput) {
      nextTick(() => {
        nextInput.focus();
      });
    }
  }
};

const handleKeyDown = (index, event) => {
  // 处理删除操作
  if (event.key === 'Backspace' && !event.target.value && index > 0) {
    const prevInput = event.target.previousElementSibling;
    if (prevInput) {
      nextTick(() => {
        prevInput.focus();
      });
    }
  }
};

// 登录逻辑
import {VerReg} from '../services/register.ts'
import {ElMessage} from "element-plus";

const submitVerificationCode = async () => {
  const code = verificationCodes.value.join('');
  if (/^\d{6}$/.test(code)) {
    const status = await VerReg(code)

    switch (status) {
      case 200:
        ElMessage.success('注册成功');
    }
  } else {
    alert('请输入有效的 6 位数字验证码');
  }
};
</script>

<style scoped>
/* 验证码容器样式 */
.verification-container {
  display: flex; /* 使用弹性布局 */
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  height: 100vh; /* 高度占满整个视口 */
  background-color: #f5f5f5; /* 背景颜色 */
}

/* 验证码输入框样式 */
.verification-box {
  width: 400px; /* 宽度 */
  padding: 40px; /* 内边距 */
  background-color: #fff; /* 背景颜色 */
  border-radius: 8px; /* 边框圆角 */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* 阴影效果 */
}

/* 标题样式 */
.title {
  margin-bottom: 24px; /* 底部外边距 */
  color: #333; /* 文字颜色 */
  text-align: center; /* 文字居中 */
  font-size: 30px; /* 字体大小 */
  font-weight: 800; /* 字体粗细 */
}

/* 描述文字样式 */
.description {
  text-align: center; /* 文字居中 */
  margin-bottom: 20px; /* 底部外边距 */
  color: #666; /* 文字颜色 */
  font-size: 14px; /* 字体大小 */
  font-weight: 600
}

/* 验证码输入框容器样式 */
.verification-inputs {
  display: flex; /* 使用弹性布局 */
  justify-content: space-between; /* 子元素之间均匀分布 */
  margin-bottom: 30px; /* 底部外边距 */
}

/* 单个验证码输入框样式 */
.verification-input {
  width: 40px; /* 宽度 */
  height: 40px; /* 高度 */
  text-align: center; /* 文字居中 */
  font-size: 18px; /* 字体大小 */
  font-weight: 600; /* 字体粗细 */
}

/* 验证按钮样式 */
.verify-button {
  width: 100%; /* 宽度占满父元素 */
  padding: 10px; /* 内边距 */
  border: none; /* 无边框 */
  border-radius: 4px; /* 边框圆角 */
  background-color: #333; /* 背景颜色 */
  color: #fff; /* 文字颜色 */
  font-size: 16px; /* 字体大小 */
  cursor: pointer; /* 鼠标指针样式 */
}
</style>