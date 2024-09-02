import axios from "../../../../axios";
import {tokenStore} from "../../../../pinia/token.ts";

async function sendMail(email: string): Promise<number> {
    try {
        const response = await axios.post(
            "user/sendLoginCode",
            {
                email: email,
            });
        return response.data.status;
    } catch (error) {
        return 408;
    }
}

async function verifyLoginCode(email: string, code: string): Promise<number> {
    try {
        const response = await axios.post(
            "user/verifyLoginCode",
            {
                email: email,
                code: code,
            }
        )
        const status = response.data.status;
        if (status === 200) {
            // 插入token
            const store = tokenStore();
            store.setToken(response.data.data);
            return status;
        } else {
            return status;
        }
    } catch (error) {
        return 408;
    }
}

export {sendMail, verifyLoginCode} ;