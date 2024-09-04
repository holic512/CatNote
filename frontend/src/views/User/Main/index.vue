<script setup lang="ts">
import {ref} from 'vue';

// 侧边栏
import Sidebar from "./components/Sidebar.vue";

const isCollapsed = ref(false);
const toggleSidebar = () => {
  isCollapsed.value = !isCollapsed.value;
};
</script>

<template>
  <div class="common-layout">
    <el-container>
      <!-- 侧边栏 -->
      <el-aside :width="isCollapsed ? '0' : '200px'" class="aside">
        <Sidebar v-if="!isCollapsed"/>
      </el-aside>

      <el-container>
        <!-- 顶栏 -->
        <el-header class="header">
          <el-row>
            <el-col :span="1">
              <button class="icon-button" @click="toggleSidebar">
                <el-icon v-if="isCollapsed">
                  <Expand/>
                </el-icon>
                <el-icon v-else>
                  <Fold/>
                </el-icon>
              </button>
            </el-col>

            <el-col :span="1">
              <button class="icon-button">
                <el-icon>
                  <DocumentAdd/>
                </el-icon>
              </button>
            </el-col>
          </el-row>
        </el-header>

        <!-- 内容 -->
        <el-main>
          Main Content
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
body {
  margin: 0;
  padding: 0;
  font-family: 'Arial', sans-serif;
  background-color: #f0f2f5;
}

.common-layout {
  height: 100vh;
}


.aside {
  transition: width 0.3s ease ;
  overflow: hidden;
  height: 100vh;
  box-shadow: 1px 0  rgba(0, 0, 0, 0.1);
}


.icon-button {
  background-color: transparent;
  border: none;
  cursor: pointer;
  padding: 5px; /* 缩小图标按钮的内边距，减少上下空白 */
  border-radius: 8px;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.icon-button:hover {
  background-color: rgba(0, 0, 0, 0.05);
  transform: scale(1.1);
}

.el-icon {
  font-size: 24px;
  color: #606266;
  transition: color 0.3s ease;
}

.icon-button:hover .el-icon {
  color: #409eff;
}

.header {
  padding-top: 8px; /* 顶栏向上移动 */

}

.el-main {
  padding: 20px;


}


</style>
