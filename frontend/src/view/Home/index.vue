<!--
* Description: 初始门户页面
* Version: 1.0
-->
<script setup lang="ts">

</script>

<template>
  <div>
    <el-container>
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
                <img src="./assets/images/shouye.svg" alt="icon" class="icon"/>
                <el-text tag="b">首页</el-text>
              </div>

              <div class="link-container">
                <img src="./assets/images/jiaru.svg" alt="icon" class="icon"/>
                <el-text tag="b">加入我们</el-text>
              </div>


            </div>
          </el-col>

        </el-row>
      </el-header>
      <el-main>
        <!--        Main-->
        <!--        Main-->s
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
</style>