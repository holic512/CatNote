import axios from "@/axios";

export const addClass = async (name: string, type: number) => {
    try {
        const response = await axios.post(
            "user/todo/addCategory",
            {
                "name": name,
                "type": type,
            }
        )
        return response.data.status;
    } catch (error) {
        return 500
    }
}