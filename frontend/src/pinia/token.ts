// * Description: 存放用户token
// * Version: 1.0
import {defineStore} from "pinia";

export const tokenStore = defineStore('token', {
    // 定义变量 存储状态数据
    state() {
        return {
            token: undefined as string | undefined
        }
    },
    // 定义 行为方法
    actions: {
        getToken(): string | undefined {
            return this.token;
        },
        setToken(token: string | undefined) {
            this.token = token;
        },
    },
    persist: true,
})
