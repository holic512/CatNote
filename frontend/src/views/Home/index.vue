<!--
* Description: 初始门户页面
* Version: 1.0
-->
<script setup lang="ts">
const items = [
  {
    title: "🛠 直观的设计",
    details: "简化笔记组织，提升书写体验，让每次记录都变得轻松愉快。",
  },
  {
    title: "🔑 类型安全",
    details: "通过先进的安全机制，确保每条笔记的数据准确无误。",
  },
  {
    title: "⚙️ Markdown 支持",
    details: "完美支持 Markdown 格式，方便快捷地格式化您的笔记内容。",
  },
  {
    title: "🔌 可扩展性",
    details: "通过事务和本地存储功能扩展，灵活应对各种自定义需求。",
  },
  {
    title: "🏗 模块化设计",
    details: "支持多模块管理，提升笔记组织的清晰度和工作效率。",
  },
  {
    title: "📦 网页端轻量化",
    details: "在网页端仍旧提供强大功能并且保持极致的轻量化。",
  },
  {
    title: "🌐 跨平台兼容性",
    details: "在任何操作系统和设备上运行，无需下载和安装，随时随地访问您的笔记。",
  },
  {
    title: "🔄 即时更新",
    details: "自动获取最新版本，无需手动更新，享受最优质的体验。",
  },
  {
    title: "📁 集中管理",
    details: "数据和设置存储在服务器端，任何设备上均可访问相同的数据和配置。",
  },
]

</script>

<template>
  <div>


    <el-container>
      <!-- 头部 -->
      <el-header class="custom-header">
        <el-row class="header-row" type="flex" justify="space-between" align="middle">
          <!-- 左半部 -->
          <el-col :span="16">
            <div class="left-part">
              <img src="./assets/images/logo.svg" alt="CatNote Logo" class="logo"/>
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
      <!--主要内容-->
      <el-main style="margin-top: 12px; margin-bottom: 12px; height: calc(100vh - 100px);">
        <el-scrollbar height="100%">

          <el-row justify="center">
            <!-- 左半部分：展示图片 -->
            <el-col :span="6" class="image-container">
              <img src="./assets/images/image1.png" alt="CatNote Logo">
            </el-col>

            <!-- 右半部分：标题和介绍 -->
            <el-col :span="8">
              <div class="title-container">
                <!-- 项目名称和标语 -->
                <el-text class="project-name">CatNote</el-text>
                <el-tag
                    round
                    type="info"
                    class="tagline"
                >
                  简洁 快速 高效
                </el-tag>
              </div>
              <div class="intro-container">
                <!-- 项目简介 -->
                <h1 class="main-title">基于Vue和SpringBoot的云笔记平台</h1>
                <h2 class="sub-title">让你的笔记管理更加轻松高效！</h2>
              </div>
              <div class="description-container">
                <!-- 项目描述 -->
                <p class="description-text">
                  CatNote 是一个专为高效笔记管理打造的云笔记平台，支持 Markdown 格式的笔记存储，提供便捷的图片处理与版本控制功能。
                  无论你是学生、职场人士还是自由职业者，CatNote 都能帮助你随时随地记录想法，保持工作条理性。
                </p>
              </div>
              <div class="button-container">
                <!-- 操作按钮 -->
                <el-button round plain>开始使用</el-button>
                <el-button round plain>GitHub</el-button>
              </div>
            </el-col>
          </el-row>

          <div class="items">
            <div class="grid item" v-for="(item, index) in items" :key="index">
              <div class="Link no-icon Feature">
                <article class="box">
                  <h2 class="title">{{ item.title }}</h2>
                  <p class="details">{{ item.details }}</p>
                </article>
              </div>
            </div>
          </div>

        </el-scrollbar>
      </el-main>
    </el-container>


  </div>
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

.link-container::after {
  content: '';
  display: block;
  position: absolute;
  left: 0;
  bottom: 0;
  width: 105%;
  height: 2px; /* 下划线的厚度 */
  background-color: transparent; /* 默认下划线颜色为透明 */
  transition: background-color 0.2s; /* 平滑过渡效果 */
}

.link-container:hover::after,
.link-container:focus::after {
  background-color: #009bff; /* 鼠标悬停或焦点时的下划线颜色 */
}

.items {
  display: flex;
  flex-wrap: wrap; /* 允许子项换行 */
  gap: 50px; /* 水平和垂直间距 */
  justify-content: space-between; /* 控制子项在容器中的对齐方式 */
  margin: 0 auto; /* 使容器在宽度范围内居中对齐 */
  max-width: 1200px; /* 设置容器的最大宽度 */
  padding: 20px; /* 容器的内边距 */
  box-sizing: border-box;
}

.item {
  flex: 1 1 calc(33.333% - 50px); /* 子项宽度，减去水平间距 */
  box-sizing: border-box;
}

.box {
  display: flex;
  flex-direction: column; /* 垂直排列内容 */
  align-items: center; /* 水平居中内容 */
  text-align: center; /* 文字居中对齐 */
  padding: 20px;
  border-radius: 10px;
  background-color: #ffffff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */
  width: 100%;
}

.title {
  font-size: 1.5rem;
  margin-bottom: 10px;
}

.details {
  font-size: 1rem;
  color: #666;
}

/* 图片容器样式 */
.image-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

/* 标题容器样式 */
.title-container {
  display: flex;
  align-items: center;
}

/* 项目名称样式 */
.project-name {
  margin-right: 10px;
  font-size: 24px;
  font-weight: bold;
  color: #333;
}

/* 标语标签样式 */
.tagline {
  background-color: #e0f7fa;
  color: #00796b;
}

/* 主标题样式 */
.main-title {
  font-size: 28px;
  color: #222;

}

/* 副标题样式 */
.sub-title {
  font-size: 20px;
  color: #555;
}

/* 描述文字样式 */
.description-text {
  font-size: 16px;
  color: #666;
  line-height: 1.6;
}
</style>