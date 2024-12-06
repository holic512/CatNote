// 待做完成
import axios from "@/axios";

export const CompleteTodo = async (todoId: number) => {
    try {
        const response = await axios.patch(
            "user/todo/complete",
            {},
            {
                params: {
                    todoId: todoId
                }
            }
        )
        return response.data.status;
    } catch (error) {
        console.error(error);
    }
}