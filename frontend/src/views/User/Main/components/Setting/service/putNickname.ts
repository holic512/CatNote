import axios from "../../../../../../axios";

const putNickname = async (nicknameA: string) => {
    try {
        const response = await axios.put(
            "user/settings/account/nickname",
            {
                nickname: nicknameA
            }
        )

        return response.data.status;
    } catch (err) {
        return 500;
    }

}

export {putNickname}