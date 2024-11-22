<template>
  <div class="app-container">
    <el-container>
      <!-- 头部 -->
      <el-header class="custom-header">
        <el-row class="header-row" type="flex" justify="space-between" align="middle">
          <!-- 左半部 -->
          <el-col :span="16">
            <div class="left-part">
              <div class="brand-name">
                <span
                    v-for="(letter, index) in brandName.split('')"
                    :key="index"
                    class="brand-letter"
                    @mouseover="animateLetter($event, index)"
                    @mouseleave="resetLetter($event)"
                >{{ letter }}</span>
              </div>
            </div>
          </el-col>

          <!-- 右半部 -->
          <el-col :span="5">
            <div class="right-part">
              <div class="link-container">
                <el-text class="link-text">首页</el-text>
              </div>
              <div class="link-container">
                <el-text class="link-text">GitHub</el-text>
              </div>
            </div>
          </el-col>
        </el-row>
      </el-header>
    </el-container>

    <div class="text-container">
      <h1 class="large-text">
        <span class="letter remember">R</span>
        <span class="letter remember">e</span>
        <span class="letter remember">m</span>
        <span class="letter remember">e</span>
        <span class="letter remember">m</span>
        <span class="letter remember">b</span>
        <span class="letter remember">e</span>
        <span class="letter remember">r</span>
        <br />
        <span class="letter indent anything">a</span>
        <span class="letter anything">n</span>
        <span class="letter anything">y</span>
        <span class="letter anything">t</span>
        <span class="letter anything">h</span>
        <span class="letter anything">i</span>
        <span class="letter anything">n</span>
        <span class="letter anything">g</span>
      </h1>

      <div class="button-container"  v-if="showButtons">
        <el-button round plain :style="{ width: '200px', height: '50px', fontSize: '1.3rem' }">开始使用</el-button>
      </div>
    </div>

    <!-- Divider -->
    <hr class="divider" />

    <!-- 项目简介 -->
    <div class="intro-section" ref="introSection">
      <div class="intro-text">
        <h1 class="main-title">基于 Vue 和 SpringBoot 的云笔记平台</h1>
        <h2 class="sub-title">让你的笔记管理更加轻松高效！</h2>
        <p class="description-text">
          SlothNote 是一个专为高效笔记管理打造的云笔记平台，支持 Markdown 格式的笔记存储，
          提供便捷的图片处理与版本控制功能。无论你是学生、职场人士还是自由职业者，
          SlothNote 都能帮助你随时随地记录想法，保持工作条理性。
        </p>
      </div>
    </div>
    <!-- 新增的直观设计部分 -->
    <hr class="divider" />
    <div class="new-info-container">
      <img src="./assets/images/logo.svg" alt="直观设计" class="new-image" /> <!-- 添加图片 -->
      <div class="new-info">
        <h3 class="new-title">🛠 直观的设计</h3>
        <p class="new-details">简化笔记组织，提升书写体验，让每次记录都变得轻松愉快。</p>
      </div>
    </div>


    <!-- 新增的类型安全部分 -->
    <hr class="divider" />
    <div class="new-info-container">
      <img src="./assets/images/logo.svg" alt="类型安全" class="new-image" /> <!-- 添加图片 -->
      <div class="new-info">
        <h3 class="new-title">🔑 类型安全</h3>
        <p class="new-details">通过先进的安全机制，确保每条笔记的数据准确无误。</p>
      </div>
    </div>

    <!-- 新增的Markdown支持部分 -->
    <hr class="divider" />
    <div class="new-info-container">
      <img src="./assets/images/logo.svg" alt="Markdown支持" class="new-image" /> <!-- 添加图片 -->
      <div class="new-info">
        <h3 class="new-title">⚙️ Markdown 支持</h3>
        <p class="new-details">完美支持 Markdown 格式，方便快捷地格式化您的笔记内容。</p>
      </div>
    </div>

    <!-- 新增的可扩展性部分 -->
    <hr class="divider" />
    <div class="new-info-container">
      <img src="./assets/images/logo.svg" alt="可扩展性" class="new-image" /> <!-- 添加图片 -->
      <div class="new-info">
        <h3 class="new-title">🔌 可扩展性</h3>
        <p class="new-details">通过事务和本地存储功能扩展，灵活应对各种自定义需求。</p>
      </div>
    </div>

    <!-- 新增的模块化设计部分 -->
    <hr class="divider" />
    <div class="new-info-container">
      <img src="./assets/images/logo.svg" alt="模块化设计" class="new-image" /> <!-- 添加图片 -->
      <div class="new-info">
        <h3 class="new-title">🏗 模块化设计</h3>
        <p class="new-details">支持多模块管理，提升笔记组织的清晰度和工作效率。</p>
      </div>
    </div>

    <!-- 新增的网页端轻量化部分 -->
    <hr class="divider" />
    <div class="new-info-container">
      <img src="./assets/images/logo.svg" alt="网页端轻量化" class="new-image" /> <!-- 添加图片 -->
      <div class="new-info">
        <h3 class="new-title">📦 网页端轻量化</h3>
        <p class="new-details">在网页端仍旧提供强大功能并且保持极致的轻量化。</p>
      </div>
    </div>

    <!-- 新增的跨平台兼容性部分 -->
    <hr class="divider" />
    <div class="new-info-container">
      <img src="./assets/images/logo.svg" alt="跨平台兼容性" class="new-image" /> <!-- 添加图片 -->
      <div class="new-info">
        <h3 class="new-title">🌐 跨平台兼容性</h3>
        <p class="new-details">在任何操作系统和设备上运行，无需下载和安装，随时随地访问您的笔记。</p>
      </div>
    </div>

    <!-- 新增的即时更新部分 -->
    <hr class="divider" />
    <div class="new-info-container">
      <img src="./assets/images/logo.svg" alt="即时更新" class="new-image" /> <!-- 添加图片 -->
      <div class="new-info">
        <h3 class="new-title">🔄 即时更新</h3>
        <p class="new-details">自动获取最新版本，无需手动更新，享受最优质的体验。</p>
      </div>
    </div>

    <!-- 新增的集中管理部分 -->
    <hr class="divider" />
    <div class="new-info-container">
      <img src="./assets/images/logo.svg" alt="集中管理" class="new-image" /> <!-- 添加图片 -->
      <div class="new-info">
        <h3 class="new-title">📁 集中管理</h3>
        <p class="new-details">数据和设置存储在服务器端，任何设备上均可访问相同的数据和配置。</p>
      </div>
    </div>

    <!-- Footer Text -->
    <div class="footer-text" ref="footerText">
      SlothNote – 为学习人士构建的非常强大的笔记工具
    </div>
  </div>
</template>

<script setup lang="ts">
import gsap from "gsap";
import {onMounted, ref} from "vue";

const introSection = ref<HTMLElement | null>(null);
const brandName = ref("SlothNote"); // 品牌名称
const footerText = ref<HTMLElement | null>(null);
const showButtons = ref(false);

onMounted(() => {
  // 使用 Intersection Observer 监听元素是否进入视口
  const observer = new IntersectionObserver(
      ([entry]) => {
        if (entry.isIntersecting) {
          // 元素进入视口时触发动画
          gsap.fromTo(
              ".intro-text",
              {
                opacity: 0,
                scale: 0.8,
                y: 50,
              },
              {
                opacity: 1,
                scale: 1,
                y: 0,
                duration: 1.5,
                ease: "power4.out",
              }
          );

          // 停止观察，防止重复触发
          observer.disconnect();
        }
      },
      { threshold: 0.3 } // 30% 可见度时触发
  );

  if (introSection.value) {
    observer.observe(introSection.value);
  }
  // 动画效果为 "Remember"
  gsap.from(".remember", {
    duration: 1.5,
    opacity: 0,
    scale: gsap.utils.random(0.8, 1.2),
    y: gsap.utils.random(-50, 0),
    rotation: gsap.utils.random(-10, 10),
    ease: "bounce.out",
    color: gsap.utils.random(["#ff6347", "#6a5acd", "#20b2aa", "#ffa07a"]),
    stagger: 0.1,
  });

  // 动画效果为 "anything"
  gsap.from(".anything", {
    duration: 1.5,
    opacity: 0,
    scale: gsap.utils.random(1, 1.5),
    x: gsap.utils.random(-50, 50),
    ease: "elastic.out(1, 0.5)",
    color: gsap.utils.random(["#ff1493", "#7fff00", "#00ced1", "#ff4500"]),
    stagger: 0.2,
  });

  // 新增的 footer 动画
  gsap.from(footerText.value, {
    duration: 1,
    opacity: 0,
    scale: 0,
    transformOrigin: "center", // 从中间展开
    ease: "back.out(1.7)", // 适当的弹性效果
    delay: 1.5, // 延迟，使其在其他动画后出现
  });

  // 延迟显示按钮
  setTimeout(() => {
    showButtons.value = true; // 根据其他动画的时长调整延迟
    gsap.fromTo('.button-container', {
      opacity: 0,
      y: 20
    }, {
      opacity: 1,
      y: 0,
      duration: 0.5,
      ease: "power2.out",
    });
  }, 1500);
});

// 动画效果为每个字母
const animateLetter = (event: MouseEvent, index: number) => {
  const target = event.target as HTMLElement;

  // 为每个字母设置不同的动画效果
  gsap.to(target, {
    scale: gsap.utils.random(1.5, 2),
    rotation: gsap.utils.random(-30, 30),
    y: gsap.utils.random(-30, 30),
    duration: 0.5,
    ease: "bounce.out",
    color: gsap.utils.random(["#ff6347", "#6a5acd", "#20b2aa", "#ffa07a"]),
    backgroundColor: gsap.utils.random(["#ffeb3b", "#f44336", "#4caf50", "#2196f3"]),
    border: '3px solid #d35400',
    borderRadius: '10px',
  });
};

// 恢复字母原始状态
const resetLetter = (event: MouseEvent) => {
  const target = event.target as HTMLElement;
  gsap.to(target, {
    scale: 1,
    rotation: 0,
    y: 0,
    duration: 0.5,
    ease: "power1.out",
    color: "#4a90e2",
    backgroundColor: 'transparent',
    border: 'none',
  });
};
</script>

<style scoped>
.text-container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-grow: 1;
  text-align: center;
  margin-top: 100px;
  position: relative; /* 允许绝对定位子元素 */
}

.button-container {
  position: absolute; /* 绝对定位 */
  right: 20px; /* 右侧留白 */
  bottom: 40px; /* 底部留白 */
  display: flex; /* 使用 Flexbox 布局 */
}

.button-container el-button {
  padding: 12px 24px; /* 添加内边距 */
  font-size: 2rem; /* 增加字体大小 */
  border-radius: 8px; /* 使按钮边角圆滑 */
  transition: background-color 3s; /* 过渡效果 */
}

.app-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.custom-header {
  background-color: rgba(255, 255, 255, 0.9); /* 背景色 */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); /* 头部阴影效果 */
  position: fixed; /* 固定头部 */
  top: 0;
  left: 0;
  right: 0;
  z-index: 10; /* 确保在其他内容之上 */
}

.header-row {
  padding: 10px 20px;
}

.left-part {
  display: flex;
  align-items: center;
}

.brand-name {
  font-size: 1.5rem;
  color: #4a90e2;
  display: flex;
}

.brand-letter {
  transition: color 0.3s ease, border 0.3s ease;
  cursor: pointer;
  padding: 5px;
  border-radius: 3px;
}

.brand-letter:hover {
  color: #d35400;
}

.right-part {
  display: flex;
  justify-content: flex-end;
}

.link-container {
  margin-left: 20px;
}

.link-text {
  color: #333;
  cursor: pointer;
}

.text-container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-grow: 1;
  text-align: center;
  margin-top: 60px;
}

.large-text {
  font-size: 15vw;
  font-weight: bold;
  line-height: 1.2;
  color: #333;
}

.letter {
  display: inline-block; /* 使每个字母独立显示 */
  transition: background-color 0.3s ease; /* 添加背景色过渡效果 */
}

.indent {
  margin-left: 2em;
}

/* 新增样式 */
.footer-text {
  position: absolute;
  bottom: 20px;
  left: 20px;
  color: #555;
  font-size: 1.2rem;
  background-color: rgba(255, 255, 255, 0.8);
  padding: 10px;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  transform-origin: center; /* 确保从中间展开 */

}


/* 项目简介部分样式 */
/* 项目简介部分样式 */
.intro-section {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh; /* 占满整个视口 */
  text-align: center;
  background: linear-gradient(135deg,#ffffff,#ffffff); /* 背景渐变 */
  color: #000000; /* 白色字体 */
  padding: 0 20px; /* 两侧留白 */
}

.intro-text {
  max-width: 800px; /* 控制文字宽度 */
  font-size: 1.2rem;
}

.main-title {
  font-size: 3rem; /* 主标题字体大小 */
  font-weight: bold;
  margin-bottom: 20px;
  text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3); /* 添加阴影 */
  line-height: 1.2;
}

.sub-title {
  font-size: 2rem; /* 副标题字体大小 */
  margin-bottom: 20px;
  font-weight: 500;
  text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2); /* 添加轻微阴影 */
}

.description-text {
  font-size: 1.5rem; /* 描述字体大小 */
  line-height: 1.8; /* 增加行高提升可读性 */
  background: rgba(0, 0, 0, 0.2); /* 背景色 */
  padding: 15px;
  border-radius: 10px; /* 圆角背景 */
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2); /* 添加阴影 */
}

/* New info container styles */
.new-info-container {
  display: flex;
  align-items: center;
  margin: 40px 0; /* Spacing around the new info */
}


.new-info {
  text-align: left; /* Align text to the left */
}

.new-title {
  font-size: 2rem; /* Font size for the new title */
  color: #4a90e2; /* Title color */
  margin-bottom: 10px; /* Space below the title */
}

.new-details {
  font-size: 1.2rem; /* Font size for the new details */
  color: #333; /* Text color */
}

.new-info-container {
  display: flex;
  align-items: center;
  margin: 40px 0; /* Spacing around the new info */
  padding: 0 20px; /* 添加左右内边距 */
}

.new-image {
  width: 180px; /* Adjust the image size */
  height: 180px; /* Adjust the image size */
  margin-right: 20px; /* Space between the image and text */
  border-radius: 10px; /* Optional: Add rounded corners */
}

</style>
