// 待做取消完成
import axios from "@/axios";

export const ReopenTodo = async (todoId: number) => {
    try {
        const response = await axios.patch(
            "user/todo/reopen",
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