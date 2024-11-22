# 前端帮助

## axios 同 pinia 使用模板

```ts
// * Description: 存放用户token
// * Version: 1.0
import {defineStore} from "pinia";

export const tokenStore = defineStore('toke', {
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

```

```ts
import axios from "../../../../axios/index.js";
import {tokenStore} from "../../../../pinia/token";

async function pwLogin(username: string, password: string): Promise<number> {
    try {
        const response = await axios.post(
            "user/login",
            {
                username: username,
                password: password,
            }
        );

        const store = tokenStore();
        store.setToken(response.data);
        console.log(store.getToken());
        return 0; // 登录成功

    } catch (error) {

        const status = error.response?.status; // 确保存在 response 对象

        if (status === 401) {
            return 1; // 认证失败
        } else if (status === 404) {
            return 2; // 用户未找到或其他 404 错误
        } else {
            console.error('未知错误:', error); // 记录错误
            return -1; // 其他错误
        }
    }
}
```

# 后端帮助

## 数据库环境配置

按照backend/.env.example的模板 在同目录下创建名为.env文件,填写公告中数据库配置

