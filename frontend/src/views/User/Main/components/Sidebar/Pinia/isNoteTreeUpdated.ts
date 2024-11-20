// 当前笔记树是否已更新，用于记录笔记树的更新状态
import {defineStore} from 'pinia'

export const useNoteTreeUpdate = defineStore('NoteTreeUpdate', {
    state() {
        return {
            isNoteTreeUpdated: false,  // 用于存储笔记树是否已更新的状态
        }
    },

    actions: {
        // 更新笔记树状态
        UpdatedNoteTree(): void {
            this.isNoteTreeUpdated = true
        },

        // 笔记树 更新完成
        UpdatedNoteTreeCompleted() {
            this.isNoteTreeUpdated = false
        }

    },

})
