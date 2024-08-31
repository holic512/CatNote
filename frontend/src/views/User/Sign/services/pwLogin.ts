import axios from "../../../../axios";
import {tokenStore} from "../../../../pinia/token.ts";

async function pwLogin(username: string, password: string): Promise<number> {
    try {
        const response = await axios.post(
            "user/PLogin",
            {
                username: username,
                password: password,
            }
        );
        const status = response.data.status;
        if (status === 200) {
            const store = tokenStore();
            store.setToken(response.data);
            return status;
        }

        return status; // 登录成功

    } catch (error) {
        return 408;
    }
}

export default pwLogin;