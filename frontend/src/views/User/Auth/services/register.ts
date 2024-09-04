// register.ts
import axios from "../../../../axios";
import {regIDStore} from "./regIDStore.ts";

async function initiateReg(username: string, password: string, email: string) {
    try {
        const response = await axios.post(
            "user/initiateReg",
            {
                username: username,
                password: password,
                email: email,
            }
        )
        // 获取状态码,并保存regID
        const status = response.data.status;
        if (status === 200) {
            const store = regIDStore();
            store.setRegID(response.data.data);
        }
        return status;
    } catch (error) {
        return 500;
    }
}

async function VerReg(code: string) {
    try {

        const regID = regIDStore().getRegID();

        const response = await axios.post(
            "user/verificationReg",
            {
                regID: regID,
                code: code,
            }
        )
        // 获取状态码,并保存regID
        return response.data.status;
    } catch (error) {
        return 500;
    }
}


export {initiateReg,VerReg};