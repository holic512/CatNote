// register.ts
import axios from "../../../../../axios";
import {regIDStore} from "../../../../../pinia/regIDStore.ts";

async function initiateReg(username: string, password: string, email: string) {
    try {
        const response = await axios.post(
            "user/auth/initiateReg",
            {
                username: username,
                password: password,
                email: email,
            }
        )
        // 获取状态码,并保存regID
        const status = response.data.status;
        if (status === 200) {
            regIDStore().setRegID(response.data.data);
        }
        return status;
    } catch (error) {
        return 500;
    }
}

// 用户注册 验证 验证码流程
async function VerReg(code: string) {
    try {
        const response = await axios.post(
            "user/auth/verificationReg",
            {
                regID: regIDStore().getRegID(),
                code: code,
            }
        )
        // 获取状态码,并保存regID
        const status = response.data.status;
        if (status === 200) {
            regIDStore().clearRegID();
        }
        return response.data.status;
    } catch (error) {
        return 500;
    }
}


export {initiateReg, VerReg};