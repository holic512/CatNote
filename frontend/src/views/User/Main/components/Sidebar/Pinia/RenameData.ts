// 用于 控制 重命名显示 的pinia
// 当前笔记树是否已更新，用于记录笔记树的更新状态
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
