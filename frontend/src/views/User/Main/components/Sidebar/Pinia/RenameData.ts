// 用于控制 右键菜单-重命名 显示 的pinia
import {defineStore} from 'pinia'


export const useRenameData = defineStore('RenameData', {
    state() {
        return {
            RenameIs: false,  // 用于存储 是否显示重命名
        }
    },

    actions: {
        // 显示 重命名 页面
        IsRename(): void {
            this.RenameIs = true
        },

        // 关闭 重命名 页面
        NoRenameIs() {
            this.RenameIs = false
        }
    },

})
