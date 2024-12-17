<script setup lang="ts">
import {computed, ref} from 'vue';

import SpeedDial from 'primevue/speeddial';
import Button from "primevue/button";

import IconBold from "@/views/User/Main/components/Edit/Main/Tools/icon/IconBold.vue";
import IconItalic from "@/views/User/Main/components/Edit/Main/Tools/icon/IconItalic.vue";
import IconBottomLeft from "@/views/User/Main/components/Edit/Main/Tools/icon/IconTopRight.vue";
import {useUserPreferencesStore} from "@/views/User/Main/Pinia/userPreferencesStore";
import {bold} from "@/views/User/Main/components/Edit/Main/Tools/Service/bold";
import {italic} from "@/views/User/Main/components/Edit/Main/Tools/Service/Italic";

// 获取个性化实例
const PreferencesStore = useUserPreferencesStore()

// 获取editor的实例
const editor: any = defineModel()

const icons = {
  BottomLeft: IconBottomLeft,

  bold: IconBold,
  Italic: IconItalic,

};

const items = ref([
  {
    label: '切换状态栏',
    icon: 'BottomLeft',
    command: () => {
      PreferencesStore.toggleEditorToolbar()
    }
  },

  {
    label: '加粗',
    icon: 'bold',
    command: () => {
      bold(editor.value);
    }
  },

  {
    label: '斜体',
    icon: 'Italic',
    command: () => {
      italic(editor.value)
    }
  },

])

const resolveIcon = (iconKey) => icons[iconKey] || null;

</script>

<template>

  <SpeedDial :model="items" direction="down" style="position: absolute;top: 25%; left: 25%; bottom: 0">
    <template #button="{ toggleCallback }">
      <el-button @click="toggleCallback" text circle>
        <el-icon size="16">
          <plus/>
        </el-icon>
      </el-button>
    </template>

    <template #item="{ item, toggleCallback }">
      <el-tooltip
          effect="dark"
          :content="item.label"
          :show-after="500"
          placement="right"
      >
        <el-button @click="toggleCallback" text circle>
          <el-icon>
            <!-- 动态组件 -->
            <component :is="resolveIcon(item.icon)"/>
          </el-icon>
        </el-button>
      </el-tooltip>
    </template>


  </SpeedDial>

</template>

<style scoped>
</style>