import axios from "@/axios";

export const GetUserTodoClasses = async () => {
    try {
        const response = await axios.get(
            "user/todo/todoCategory"
        )
        return response.data;
    } catch (error) {
        console.log(error);
    }
}