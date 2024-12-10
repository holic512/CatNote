<script setup lang="ts">
const StatDatas = [
  {
    id: 1,
    label: "个人笔记",
    description: "记录日常生活和工作中的点滴，整理个人的思想和心得。",
  },
  {
    id: 2,
    label: "待办事项",
    description: "整理需要完成的任务清单，帮助管理工作和生活中的重要事项。",
  },
  {
    id: 3,
    label: "学习笔记",
    description: "记录学习过程中遇到的知识点，帮助巩固学习内容。",
  },
  {
    id: 4,
    label: "项目总结",
    description: "记录项目的进度、成果以及遇到的问题和解决方案，便于总结和复盘。",
  },
  {
    id: 5,
    label: "灵感笔记",
    description: "捕捉突然想到的创意、灵感，方便日后实现或优化。",
  },
  {
    id: 6,
    label: "阅读笔记",
    description: "记录读书过程中遇到的重要观点和自己的感悟，帮助深度理解书籍内容。",
  },
  {
    id: 7,
    label: "会议纪要",
    description: "记录会议中的要点、决策和行动项，确保信息不丢失。",
  },
  {
    id: 8,
    label: "旅行计划",
    description: "规划和记录旅行中的行程安排、景点推荐以及注意事项。",
  },
  {
    id: 9,
    label: "健康记录",
    description: "记录日常饮食、运动、健康检查等数据，帮助管理个人健康。",
  },
  {
    id: 10,
    label: "财务管理",
    description: "跟踪个人财务状况，记录收入、支出和预算。",
  },
];
</script>

<template>
  <div style="display: flex;flex-direction: column;height: 100%;">

    <div class="sidebar-header">
      <div class="sidebar-icon">
        <i class="pi pi-star-fill"/>
      </div>
      <el-text tag="b" size="large">我的收藏</el-text>
    </div>


    <el-scrollbar class="scrollbar-content">
      <div v-for="i in StatDatas" :key="i.id" class="stat-item">
        <div class="stat-item-header">
          <el-text size="large">{{ i.label }}</el-text>

          <i class="pi pi-star-fill stat-item-icon"/>
        </div>
        <div class="stat-item-body">
          <el-text>{{ i.description }}</el-text>
        </div>
      </div>
    </el-scrollbar>

  </div>
</template>

<style scoped>
.sidebar-header {
  height: 48px;
  border-bottom: #F2F5F6 1px solid;
  display: flex;
  align-items: center;
  padding-left: 16px;
  gap: 8px;
}

.sidebar-icon {
  font-size: 1rem;
  color: #708090;
}

.stat-item {
  flex: 1;
  height: 128px;
  border: 1px solid #F2F5F6;
  border-radius: 4px;
  background-color: #fff;
  margin: 8px;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.stat-item-header {
  border-bottom: 1px solid #F2F5F6;
  padding: 8px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: #F9FAFB;
}

.stat-item-body {
  padding: 8px;
  flex-grow: 1;
}

.stat-item-icon {
  font-size: 1rem;
  color: #FFD700; /* 金色 */
}

.stat-item:hover {
  transform: translateY(-4px); /* 向上浮动 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 添加轻微的阴影效果 */
}

.el-text {
  color: #333;
}

.scrollbar-content {
  width: 100%;
  padding: 8px;
}
</style>
