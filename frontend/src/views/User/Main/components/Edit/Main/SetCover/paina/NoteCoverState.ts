// 用于控制 右键菜单-编辑简介 显示 的pinia
import {defineStore} from 'pinia'


export const useNoteCoverState = defineStore('NoteCover', {
    state() {
        return {
            NoteCoverVis: false,  // 用于存储 是否显示编辑背景
        }
    },

    actions: {
        // 显示 编辑简介 页面
        IsNoteCover(): void {
            this.NoteCoverVis = true
        },

        // 关闭 编辑简介 页面
        NoNoteCover() {
            this.NoteCoverVis = false
        }
    },

})
