<script setup lang="ts">
import {Plus} from '@element-plus/icons-vue'
import {onMounted, ref, watch} from "vue";
import {useTodoState} from "@/views/User/Main/components/TodoList/Pinia/TodoState";
import {getTodayDate} from "@/views/User/Main/components/TodoList/Service/getTodayDate";
import {getAllTodoList} from "@/views/User/Main/components/TodoList/TodoMain/Service/getAllTodoList";
import {TodoTypeById} from "@/views/User/Main/components/TodoList/TodoMain/Service/TodoTypeById";

// 初始化状态变量
const todoState = useTodoState()

// 监听todoState是否改变
watch(() => todoState.state, async (newValue) => {
  switch (newValue) {
    case 0:
      pageTitle.value = "全部待办";
      TodoData.value = await getAllTodoList();
      break;

    case 1:
      pageTitle.value = "今天待办(" + getTodayDate() + ")";
      break;
  }

})

// 初始化
onMounted(async () => {
  TodoData.value = await getAllTodoList();
})

// 页面标题
const pageTitle = ref<string>("全部待办")

// 进行中和已完成 显示 的变量
const isInProgressVisible = ref(true)
const isCompletedVisible = ref(true)

interface Todo {
  todo_id: number;
  title: string;
  description: string;
  startDate: string;
  dueDate: string;
  status: number;
  category_id: number;
  category_name: string;
  category_type: number;
  todoInfoisDeleted: boolean;
}


// 所有待做的 树结构
const TodoData = ref<Todo[]>([]);


// 进行中的 点击函数
const handInProgress = () => {
  isInProgressVisible.value = !isInProgressVisible.value
}

// 已完成的 点击函数
const handCompleted = () => {
  isCompletedVisible.value = !isCompletedVisible.value
}


</script>

<template>
  <div class="TodoMain">
    <!--标题-->
    <div>
      <el-text style="font-size: 24px;">{{ pageTitle }}</el-text>
    </div>

    <!--添加输入框-->
    <div style="margin-top: 16px">
      <el-input :prefix-icon="Plus" size="large" placeholder="添加待办事件,按回车保存"/>
    </div>


    <!--待做树-->
    <div style="flex: 1; margin-top: 16px">
      <el-scrollbar>
        <div style="margin-bottom: 24px">
          <div>
            <!--  进行中缩放标签  -->
            <el-text style="font-size: 16px;margin-top: 8px;user-select: none;" @click="handInProgress">
              <el-icon size="12" v-if="!isInProgressVisible">
                <ArrowRight/>
              </el-icon>
              <el-icon size="12" v-else>
                <ArrowDown/>
              </el-icon>
              进行中
            </el-text>
          </div>


          <!-- 进行中 数据树 -->
          <div v-for="task in TodoData.filter(t => t.status === 0)" :key="task.todo_id" class="task-item"
               v-if="isInProgressVisible">
            <div class="task-content">

              <!-- 选中框 -->
              <div class="task-checkbox">
                <el-checkbox/>
              </div>


              <div class="task-label">
                <el-text>
                  {{ task.title }}
                </el-text>
              </div>

              <div class="task-class">
                <el-tag :type=TodoTypeById(task.category_type) size="small">
                  {{ task.category_name || "未分类" }}
                </el-tag>
              </div>


              <div class="task-due-date">
                <el-text size="small" style="color: #7f8c8d;">
                  {{ task.dueDate }}
                </el-text>
              </div>


            </div>
            <div class="bottom-border"/>
          </div>
        </div>


        <div>
          <!--  已完成 缩放标签  -->
          <div @click="handCompleted">
            <el-text style="font-size: 16px;margin-top: 8px;user-select: none;">
              <el-icon size="12" v-if="!isCompletedVisible">
                <ArrowRight/>
              </el-icon>
              <el-icon size="12" v-else>
                <ArrowDown/>
              </el-icon>
              已完成
            </el-text>
          </div>


          <!-- 已完成 数据树 -->
          <div v-if="isCompletedVisible" v-for="task in TodoData.filter(t => t.status === 1)" :key="task.todo_id"
               class="task-item">
            <div class="task-content">
              <el-checkbox class="task-checkbox"></el-checkbox>

              <div class="task-label">
                <el-text>
                  {{ task.title }}
                </el-text>
              </div>

              <div class="task-class">
                <el-tag :type=TodoTypeById(task.category_type) size="small">
                  {{ task.category_name || "未分类" }}
                </el-tag>
              </div>

              <div class="task-due-date">
                <el-text size="small" style="color: #7f8c8d;">
                  {{ task.dueDate }}
                </el-text>
              </div>
            </div>
            <div class="bottom-border"/>

          </div>
        </div>

      </el-scrollbar>
    </div>

  </div>
</template>

<style scoped>
.TodoMain {
  border-right: 1px solid #ddd; /* 边框 */
  height: 100%;
  padding: 16px;

  display: flex;
  flex-direction: column;
}

.task-item {
  width: 100%;
  border-radius: 4px; /* 圆角效果 */
  margin: 8px 0; /* 给每个任务添加间距 */
  padding: 10px 8px 0;

}

.task-item:hover {
  background-color: #F0F2F5;
}

.bottom-border {
  width: 95%; /* 设置边框的长度 */
  height: 1px;
  background-color: #ddd; /* 设置边框颜色 */
  margin-left: 12px;

}


.task-content {
  display: flex;

}

.task-checkbox {
  margin-left: 12px;
  margin-right: 12px;
  margin-bottom: 4px;

}

.task-label, .task-class, .task-due-date {
  margin-top: 4px;
  flex: 5;
}

</style>