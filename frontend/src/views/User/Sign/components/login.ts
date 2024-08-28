import axios from "../../../../axios/index.ts";
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

export default pwLogin;