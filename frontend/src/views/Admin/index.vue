<script setup lang="ts">
import { onMounted } from 'vue';

const setupInputListeners = () => {
  const phoneInput = document.querySelector('.PhoneNumber') as HTMLInputElement;
  const passwordInput = document.querySelector('.InputPasswd') as HTMLInputElement;

  // 当输入框获得焦点时，如果其值是默认的占位符文本（请输入用户名），则清空输入框内容。
  if (phoneInput) {
    phoneInput.addEventListener('focus', () => {
      if (phoneInput.value === '请输入用户名') {
        phoneInput.value = '';
      }
    });
    // 当输入框失去焦点时，如果其值为空，则恢复为默认的占位符文本（请输入用户名）。
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

onMounted(() => {
  setupInputListeners();
});
</script>

<template>
  <el-header class="custom-header">
    <el-row class="header-row" type="flex" justify="space-between" align="middle">
      <!-- 左半部 -->
      <el-col :span="16">
        <div class="left-part">
          <img src="../Home/assets/images/logo.svg" alt="CatNote Logo" class="logo"/>
          <el-text tag="b">CatNote</el-text>
        </div>
      </el-col>

      <!-- 右半部 -->
      <el-col :span="5">
        <div class="right-part">

          <div class="link-container">
            <!-- <img src="./assets/images/shouye1.svg" alt="icon" class="icon"/>-->
            <el-text>首页</el-text>
          </div>
          <div class="link-container">
            <el-text>GitHub</el-text>
          </div>


        </div>
      </el-col>

    </el-row>
  </el-header>
  <el-container class="container">
    <div class="login">
      <br>
      <div class="phone-wrapper">
        <div class="inner-container">
          <!--用户名输入框-->
          <input class="PhoneNumber" value="请输入用户名" @click="CancelBorder">
        </div>
      </div>
      <br>
      <div class="input-group">
          <!--密码输入框-->
        <input class="InputPasswd" value="请输入密码" />
      </div>
      <br>
      <button class="logBtn">登录</button>
      <br>
      <router-link to="" class="FreeRegister">注册</router-link>
    </div>
  </el-container>
</template>

<style scoped>
.custom-header {
  height: 50px;
  border-bottom: 1px solid #e0e0e0;
}

.header-row {
  height: 100%; /* 使 el-row 高度填满 el-header */
}

.left-part {
  display: flex;
  align-items: center;
  margin-left: 12px;
  margin-bottom: 5px;
}

.logo {
  width: 32px; /* 根据需要调整 logo 大小 */
  height: 32px; /* 确保高度与宽度一致以保持圆形 */
  border-radius: 15%; /* 设置圆角半径为 50% 以实现圆形效果 */
  margin-left: 5px;
  margin-right: 8px;
}

.right-part {
  display: flex;
  align-items: center;
}

.icon {
  width: 28px; /* 根据需要调整图标大小 */
  height: 28px; /* 确保图标宽高一致 */
  margin-right: 1px; /* 图片与文本之间的间距 */
}

.link-container {
  display: flex;
  align-items: center; /* 垂直居中对齐图片和文本 */
  cursor: pointer; /* 鼠标指针变为手型 */

  position: relative; /* 使下划线定位相对于容器 */
  padding-bottom: 2px; /* 为下划线留出空间 */

  margin-right: 16px;

}


.container {
  display: flex; /*使用 Flexbox 布局模型来水平和垂直居中对齐*/
  justify-content: center;
  align-items: center;
  height: 80vh;  /*设置其高度为视口高度的 80%。*/
}

.login {
  font-size: 23px;
  display: flex;
  flex-direction: column;
  align-items: center;  /*垂直排列子元素，并居中对齐。*/
}

.phone-wrapper {
  display: flex;
  justify-content: center;  /*水平居中对齐内部的内容。*/
}

.inner-container {
  position: relative;
}


.PhoneNumber {
  padding-left: 30px;
  width: 360px;
  height: 40px;
  border-radius: 5px;
  border: none;
  background-color: rgba(125, 125, 125, 0.1);
  font-size: 18px;
  outline: none;
}

.input-group {
  position: relative;
  display: flex;
  align-items: center;
  width: 100%;
}

.InputPasswd {
  flex: 1;
  padding-left: 30px;
  height: 40px;
  border-radius: 5px;
  border: none;
  background-color: rgba(125, 125, 125, 0.1);
  font-size: 18px;
  outline: none;
}


input:focus {
  border: none;
  outline: none;
}

.FreeRegister{
  color: black;
  text-decoration: none;
}


.logBtn {
  border-radius: 5px;
  border: 0;
  background-color: aqua;
  font-size: 20px;
  color: white;
  width: 100%;
  height: 50px;
  cursor: pointer;
}

.FreeRegister {
  font-weight: bold;
  align-self: center;
  color: black;
  text-decoration: none;
}

</style>
