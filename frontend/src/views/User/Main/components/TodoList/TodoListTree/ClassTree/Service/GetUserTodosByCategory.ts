import axios from "@/axios"

export const getUserTodosByCategory = async (CategoryId: number) => {
    try {
        const response = await axios.get(
            "user/todo/TodosByCategory",
            {
                params: {
                    categoryId: CategoryId
                }
            }
        )
        return response.data;
    } catch (error) {
        console.error(error);
    }
}