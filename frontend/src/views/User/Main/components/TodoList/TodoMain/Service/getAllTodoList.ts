import axios from "@/axios";

export const getAllTodoList = async () => {
    try {
        const response = await axios.get(
            "user/todo/all"
        )
        return response.data
    } catch (error) {
        console.log(error);
    }
}