<script setup lang="ts">
import {useRouter} from "vue-router";

const items = [
  {
    title: "🛠 直观的设计",
    details: "我们提供简洁直观的设计，让笔记的组织和管理变得轻松高效。优化的界面和交互方式，使得每次记录都成为愉悦的体验，帮助您快速捕捉灵感和重要信息。",
  },
  {
    title: "🔑 类型安全",
    details: "通过先进的类型安全机制，确保每一条笔记的数据都能准确无误地存储和访问。系统自动检查数据一致性，避免错误，提高您的工作效率和数据安全性。",
  },
  {
    title: "⚙️ Markdown 支持",
    details: "我们全面支持 Markdown 格式，让您可以轻松格式化文本、添加图片和链接，制作更加结构化和专业的笔记。无论是写作还是技术记录，Markdown 都是最便捷的工具。",
  },
  {
    title: "🔌 可扩展性",
    details: "通过强大的扩展功能，如事务处理和本地存储，您可以根据需求定制应用，满足各种复杂的使用场景。灵活的架构支持让您能够不断扩展和优化工作流程。",
  },
  {
    title: "🏗 模块化设计",
    details: "模块化设计让您可以根据实际需求进行个性化定制，多模块管理提升了笔记的组织性和可操作性。无论是个人使用还是团队协作，模块化都能提供最佳的支持。",
  },
  {
    title: "📦 网页端轻量化",
    details: "网页端不仅提供与桌面版同样强大的功能，还特别注重性能优化，保持极致的轻量化，确保无论网络环境如何，您都能享受到快速、流畅的体验。",
  },
  {
    title: "🌐 跨平台兼容性",
    details: "我们的平台支持多种操作系统和设备，确保您的笔记随时随地都可以访问和同步。无需下载安装，直接在浏览器中启动，无论是手机、平板还是电脑，都能无缝连接。",
  },
  {
    title: "🔄 即时更新",
    details: "自动更新功能确保您始终使用最新版本，无需手动下载或安装，确保新功能和修复即时到位，给您带来最优质、最流畅的使用体验。",
  },
  {
    title: "📁 集中管理",
    details: "所有的数据和设置都存储在云端，确保您的笔记在任何设备上都能保持同步。不仅如此，您可以随时在不同设备之间切换，数据始终保持一致，保障工作流程的连续性和高效性。",
  },
];

// 注册 router 路由
const router = useRouter();
</script>
<template>
  <div class="app-container">


    <!--     头部-->
    <div class="custom-header">
      <img src="/favicon.ico" style="width: 24px;" alt="logo"/>
      <el-text>首页</el-text>
      <el-text>Bilibili</el-text>
      <el-link href="https://github.com/holic512/SlothNote">GitHub</el-link>
      <el-link @click="router.push('/user/auth')">登录</el-link>

    </div>
    <el-scrollbar>
      <!--特效块-->
      <div class="color-transition"></div>

      <!-- title 1-->
      <div style="padding-left: 18%; margin-bottom: 96px;margin-top: 96px">
        <div>
          <el-text class="title1" tag="b">记下,</el-text>
          <br>
          <el-text class="title1" tag="b"> 所有的一切.</el-text>
        </div>
        <div style="margin-top: 24px;">
          <el-text class="linear-gradation-title" tag="b" style="font-size: 36px">SlothNote</el-text>
          <el-text style="font-size: 24px">
            帮助你和你的团队轻松
          </el-text>
          <br>
          <el-text style="font-size: 24px"> 高效地完成更多任务,且心无旁骛.</el-text>
        </div>


      </div>

      <div
          style="display: flex; align-items: center;justify-content: center;flex-direction: column;margin-bottom: 36px">
        <el-image src="/image-removebg-preview.png"/>
        <el-text style="font-size: 48px" class="linear-gradation-title">SlothNote</el-text>
        <el-text size="large">创造一些美丽的东西.</el-text>
      </div>


      <!-- title 2-->
      <div style="height: 120px; display: flex; align-items: center;padding-left: 10%; ">
        <el-text class="title2 linear-gradation-title" tag="b">功能.</el-text>
        <el-text class="title2">为您打开了无限可能的大门</el-text>
      </div>

      <!--功能介绍-->
      <div style="padding: 0 10% 140px 10%;">
        <el-scrollbar>
          <div style="display: flex; gap: 36px;padding:8px">
            <div class="method-div" v-for="(item, index) in items" :key="index">
              <div>
                <h1>{{ item.title }}</h1>
                <el-text size="large">{{ item.details }}</el-text>

              </div>
            </div>
          </div>
        </el-scrollbar>
      </div>


      <div class="beian-container">
        <el-text>
          Copyright © 2024-2024 树懒笔记 slothnote.cn All Rights Reserved. 备案号：
        </el-text>
        <a href="https://beian.miit.gov.cn/" target="_blank" class="beian-link">
          黑ICP备2024032772号
        </a>
      </div>


    </el-scrollbar>
  </div>
</template>


<style scoped>
.app-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.custom-header {
  background-color: #F5F5F7; /* 背景色 */
  position: fixed; /* 固定头部 */
  top: 0;
  left: 0;
  right: 0;
  z-index: 10; /* 确保在其他内容之上 */

  display: flex;
  align-items: center;
  justify-content: center;

  padding: 8px;
  gap: 24px;

  height: 40px;
}

.color-transition {
  width: 100vw;
  height: 32px;
  margin-top: 40px;
  /* 使用线性渐变背景来实现逐渐透明的阴影效果 */

  background: linear-gradient(to bottom, rgba(255, 255, 255, 0) 1%, rgba(255, 255, 255, 1) 100%),
  linear-gradient(to right, white, #8EBCF3 10%, #9185ED, #F675B9, #F9CFA8 90%, white);

  background-size: 100% 100%; /* 背景的宽度和高度填满整个元素 */

}

.title1 {
  font-size: 58px;
  color: black;
}

.linear-gradation-title {
  background: linear-gradient(to right, #8EBCF3, #9185ED, #F675B9); /* 定义渐变颜色 */
  -webkit-background-clip: text; /* 使渐变应用到文字 */
  color: transparent; /* 使文字本身透明，以便只显示背景渐变 */
}

.title2 {
  font-size: 24px;
}

.method-div {
  width: 320px;
  flex-shrink: 0;
  height: 360px;
  background: #F6F5F4;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.method-div:hover {
  transform: translateY(-10px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
}


.beian-container {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 14px;
  margin-top: 20px;

  padding: 36px;
  background-color: #F5F5F5;
}

.beian-link {
  color: #007bff;
  text-decoration: none;
  font-weight: 500;
  margin: 0 8px;
  display: flex;
  align-items: center;
}
</style>
