// 用于控制 右键菜单-属性 显示 的pinia
import {defineStore} from 'pinia'


export const useDetailsState = defineStore('DetailsState', {
    state() {
        return {
            DetailsIs: false,  // 用于存储 是否显示属性
        }
    },

    actions: {
        // 显示 属性 页面
        IsDetails(): void {
            this.DetailsIs = true
        },

        // 关闭 属性 页面
        NoDetails() {
            this.DetailsIs = false
        }
    },

})
