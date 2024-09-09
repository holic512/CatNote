// login.ts
import axios from "../../../../axios";
import {logIDStore} from "./logIDStore.ts";
import {tokenStore} from "../../../../pinia/token.ts";
import {UnwrapRef} from "vue";


async function login(username: string, password: string) {
    try {
        const response = await axios.post(
            "admin/login",
            {
                username: username,
                password: password,
            }
        );
        const status = response.data.status;
        if (status === 200) {
            logIDStore().setLogID(response.data.data);
            return status;
        }
        // 成功请求验证 准备进行邮箱验证
        return status;
    } catch (error) {
        return 500;
    }
}

async function verCode(code: UnwrapRef<string>) {
    try {
        const logID = logIDStore().getLogID()
        const response = await axios.post(
            "admin/verLogin",
            {
                code: code,
                logID: logID,
            }
        )
        const status = response.data.status;
        if (status === 200) {
            tokenStore().setToken(response.data.data);
        }
        return status;
    } catch (error) {
        return 500;
    }
}

export {login, verCode};