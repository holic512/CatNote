// 用于控制 右键菜单-编辑简介 显示 的pinia
import {defineStore} from 'pinia'


export const useDescriptionState = defineStore('DescriptionState', {
    state() {
        return {
            DescriptionVis: false,  // 用于存储 是否显示编辑简介
        }
    },

    actions: {
        // 显示 编辑简介 页面
        IsDescription(): void {
            this.DescriptionVis = true
        },

        // 关闭 编辑简介 页面
        NoDescription() {
            this.DescriptionVis = false
        }
    },

})
