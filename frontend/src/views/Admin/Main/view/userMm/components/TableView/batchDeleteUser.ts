import axios from "../../../../../../../axios";

export const BatchDeleteUser = async (userIds: number[]) => {
    try {
        const response = await axios.post(
            "/admin/userMm/batchDeleteUser",
            userIds
        )
        return response.data.status;
    } catch (err) {
        return 500;
    }
}