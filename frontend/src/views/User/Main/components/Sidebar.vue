<script setup lang="ts">
import Button from 'primevue/button';
import PanelMenu from 'primevue/panelmenu';
import {ref} from 'vue';

const items = ref([
  {
    label: 'Files',
    icon: 'pi pi-file',
    items: [
      {
        label: 'Documents',
        icon: 'pi pi-file',
        items: [
          {
            label: 'Invoices',
            icon: 'pi pi-file-pdf',
            items: [
              {
                label: 'Pending',
                icon: 'pi pi-stop'
              },
              {
                label: 'Paid',
                icon: 'pi pi-check-circle'
              }
            ]
          },
          {
            label: 'Clients',
            icon: 'pi pi-users'
          }
        ]
      },
      {
        label: 'Images',
        icon: 'pi pi-image',
        items: [
          {
            label: 'Logos',
            icon: 'pi pi-image'
          }
        ]
      }
    ]
  },
  {
    label: 'Cloud',
    icon: 'pi pi-cloud',
    items: [
      {
        label: 'Upload',
        icon: 'pi pi-cloud-upload'
      },
      {
        label: 'Download',
        icon: 'pi pi-cloud-download'
      },
      {
        label: 'Sync',
        icon: 'pi pi-refresh'
      }
    ]
  },
  {
    label: 'Devices',
    icon: 'pi pi-desktop',
    items: [
      {
        label: 'Phone',
        icon: 'pi pi-mobile'
      },
      {
        label: 'Desktop',
        icon: 'pi pi-desktop'
      },
      {
        label: 'Tablet',
        icon: 'pi pi-tablet'
      }
    ]
  }
]);
</script>


<template>
  <el-divider/>

  <div class="card flex justify-center flex-wrap gap-4 sidebar-buttons">
    <Button type="button" label="主页" icon="pi pi-home" outlined text size="small"/>
    <Button type="button" label="消息" icon="pi pi-envelope" badge="2" badgeSeverity="contrast" outlined text
            size="small"/>
    <Button type="button" label="搜索" icon="pi pi-search" outlined text size="small"/>
    <Button type="button" label="设置" icon="pi pi-cog" outlined text size="small"/>
  </div>

  <el-divider/>

</template>

<style scoped>
.sidebar-buttons Button {
  width: 100%;
  justify-content: flex-start; /* 将按钮内容左对齐 */
  text-align: left;
  padding-left: 16px; /* 根据需要调整内边距 */
}


</style>
