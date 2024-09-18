// * Description: 存放用户token
// * Version: 1.0
import {defineStore} from "pinia";

export const tokenStore = defineStore('token', {
    // 定义变量 存储状态数据
    state() {
        return {
            UserToken: undefined as string | undefined,
            AdminToken: undefined as string | undefined
        }
    },
    // 定义 行为方法
    actions: {
        // 操作用户token
        getUserToken(): string | undefined {
            return this.UserToken;
        },
        setUserToken(UserToken: string | undefined) {
            this.UserToken = UserToken;
        },
        clearUserToken(): void {
            this.UserToken = undefined;
        },

        // 操作管理员token
        getAdminToken(): string | undefined {
            return this.AdminToken;
        },
        setAdminToken(AdminToken: string | undefined) {
            this.AdminToken = AdminToken;
        },
        clearAdminToken(): void {
            this.AdminToken = undefined;
        }


    },
    persist: true,
})
