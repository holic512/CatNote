<template>
  <div class="home">
    <div :class="['sidebar', { 'sidebar-closed': !isSidebarOpen }]">
      <Sidebar />
    </div>
    <div :class="['main-content', { 'content-expanded': isSidebarOpen }]">
      <div class="content-wrapper">
        <div class="content">
          <div class="header">
            <h1>欢迎来到 CatNote</h1>
            <p>你的智能云笔记助手</p>
          </div>

          <div class="note-preview">
            <h2>最近的笔记</h2>
            <ul>
              <li v-for="note in recentNotes" :key="note.id" @click="viewNote(note.id)">
                <h3>{{ note.title }}</h3>
                <p>{{ note.preview }}</p>
              </li>
            </ul>
          </div>

          <div class="quick-actions">
            <h2>快捷操作</h2>
            <div class="actions-container">
              <Button label="新建笔记" icon="pi pi-plus" class="action-button" @click="createNote" />
              <Button label="搜索笔记" icon="pi pi-search" class="action-button" @click="searchNotes" />
            </div>
          </div>

          <div class="stats">
            <h2>统计信息</h2>
            <p>总笔记数: {{ noteCount }}</p>
            <p>总分类数: {{ categoryCount }}</p>
          </div>
        </div>
      </div>
      <Button icon="pi pi-arrow-left" class="toggle-sidebar-button" @click="toggleSidebar" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import Button from 'primevue/button';
import Sidebar from './components/Sidebar.vue';

const isSidebarOpen = ref(true);

const recentNotes = ref([
  { id: 1, title: '笔记一', preview: '这是笔记一的预览内容...' },
  { id: 2, title: '笔记二', preview: '这是笔记二的预览内容...' },
  { id: 3, title: '笔记三', preview: '这是笔记三的预览内容...' }
]);

const noteCount = ref(123);
const categoryCount = ref(5);

function viewNote(id: number) {
  console.log(`查看笔记 ${id}`);
}

function createNote() {
  console.log('创建新笔记');
}

function searchNotes() {
  console.log('搜索笔记');
}

function toggleSidebar() {
  isSidebarOpen.value = !isSidebarOpen.value;
}
</script>

<style scoped>
.home {
  display: flex;
  height: 100vh;
  overflow: hidden;
}

.sidebar {
  position: fixed;
  top: 0;
  left: 0;
  width: 300px;
  height: 100%;
  background-color: #ffffff;
  border-right: 1px solid #e0e0e0;
  padding: 1rem;
  display: flex;
  flex-direction: column;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;
  z-index: 1;
}

.sidebar-closed {
  transform: translateX(-100%);
}

.main-content {
  flex: 1;
  padding: 1rem;
  margin-left: 300px;
  transition: margin-left 0.3s ease;
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.content-expanded {
  margin-left: 300px;
}

.sidebar-closed ~ .main-content {
  margin-left: 0;
}

.content-wrapper {
  display: flex;
  flex: 1;
  justify-content: center;
  align-items: center;
}

.content {
  width: 100%;
  max-width: 1200px;
  padding: 1rem;
  box-sizing: border-box;
}

.header {
  text-align: center;
  margin-bottom: 2rem;
}

.header h1 {
  font-size: 2rem;
  color: #333;
}

.header p {
  font-size: 1rem;
  color: #666;
}

.note-preview, .quick-actions, .stats {
  margin-top: 2rem;
  background-color: #ffffff;
  border-radius: 0.5rem;
  padding: 1rem;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.note-preview ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.note-preview li {
  padding: 0.5rem;
  border-bottom: 1px solid #e0e0e0;
  cursor: pointer;
}

.note-preview li:last-child {
  border-bottom: none;
}

.note-preview h3 {
  margin: 0 0 0.5rem;
  font-size: 1.125rem;
  color: #333;
}

.note-preview p {
  margin: 0;
  color: #666;
}

.quick-actions {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.actions-container {
  display: flex;
  gap: 1rem;
}

.action-button {
  border: none;
  padding: 0.75rem 1rem;
  font-size: 0.875rem;
}

.stats {
  text-align: center;
}

.toggle-sidebar-button {
  position: fixed;
  top: 1rem;
  right: 1rem;
  z-index: 1000;
  background-color: #000000;
  color: white;
  border: none;
  padding: 0.75rem;
  border-radius: 0.5rem;
  cursor: pointer;
}
</style>
