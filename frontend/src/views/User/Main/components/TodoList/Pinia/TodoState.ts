// stores/currentNoteInfo.ts
import {defineStore} from 'pinia';


export const useTodoState = defineStore('TodoState', {
    state() {
        return {
            /**
             笔记ID
             全部待办:0 今日待办:1
             */
            state: 0,
        }

    },
    actions: {
        // 切换全部待办
        ToAll() {
            this.state = 0;
        },
        // 切换今日待办
        ToToday() {
            this.state = 1;
        }
    },

});
