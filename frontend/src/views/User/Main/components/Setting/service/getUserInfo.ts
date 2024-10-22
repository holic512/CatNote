import axios from "../../../../../../axios";

export interface AccountInfo {
    id: number;
    avatar: string;
    nickname: string;
    uid: string;
    username: string;
    email: string;
    gender: string;
    age: number;
    contactInfo: string;
    bio: string;
}

const fetchAccountInfo = async () => {
    try {
        const response = await axios.get(
            "/user/settings/account/userAllProfile",
        );
        return response.data.data;
    } catch (error) {
        return 500;
    }
}

export {fetchAccountInfo}