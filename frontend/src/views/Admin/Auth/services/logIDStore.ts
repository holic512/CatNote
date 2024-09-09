// 存放regID
import {defineStore} from "pinia";

export const logIDStore = defineStore('logID', {
    // 定义变量 存储状态数据
    state() {
        return {
            logID: undefined as string | undefined
        }
    },
    // 定义 行为方法
    actions: {
        getLogID(): string | undefined {
            return this.logID;
        },
        setLogID(logID: string | undefined): void {
            this.logID = logID;
        },
    },
})