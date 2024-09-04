// 存放regID
import {defineStore} from "pinia";

export const regIDStore = defineStore('regID', {
    // 定义变量 存储状态数据
    state() {
        return {
            regID: undefined as string | undefined
        }
    },
    // 定义 行为方法
    actions: {
        getRegID(): string | undefined {
            return this.regID;
        },
        setRegID(regID: string | undefined) {
            this.regID = regID;
        },
    },
    persist: true,
})