import axios from "../../../../../../../axios";

export const onlineUserCount = async () => {
    try {
        const response = await axios.get(
            "admin/oUserMm/OUserCount"
        )
        return response.data
    } catch (err) {
        console.error(err);
        return
    }
}