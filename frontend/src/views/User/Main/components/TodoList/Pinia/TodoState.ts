// stores/currentNoteInfo.ts
import {defineStore} from 'pinia';

interface State {
    // 描述更新状态
    isDescriptionVisible: boolean

    /**
     页面状态 标识符
     全部待办:0 今日待办:1 分类待办:2
     */
    state: number;

    /**
     * 用来存储 点击分类的 信息
     */
    AClass: any;
}

export const useTodoState = defineStore('TodoState', {
    state(): State {
        return {
            isDescriptionVisible: true,
            state: 0,
            AClass: null,
        }

    },
    actions: {
        // 切换全部待办
        ToAll() {
            // 触发更新操作
            this.description()

            this.state = 0;
        },

        // 切换今日待办
        ToToday() {
            // 触发更新操作
            this.description()

            this.state = 1;
        },

        ToClass(class1: any) {
            // 触发更新操作
            this.description()

            this.clearClassId();
            this.state = 2;
            this.AClass = class1;
        },

        description() {
            this.isDescriptionVisible = !this.isDescriptionVisible;
        },

        clearClassId() {
            this.AClass = null;
        }
    },

});
