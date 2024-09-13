// * Version: 1.0
import {defineStore} from "pinia";

export const routerStore = defineStore('route', {
    // 定义变量 存储状态数据
    state() {
        return {
            // 判断路由守卫是否触发
            guard: false
        }
    },
    // 定义 行为方法
    actions: {
        getGuard(): boolean {
            return this.guard;
        },
        onGuard(): void {
            this.guard = true;
        },
        offGuard(): void {
            this.guard = false;
        }
    },
})
