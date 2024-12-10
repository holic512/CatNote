import axios from "@/axios";

export const upNoteCover = async (noteId: number, noteCover: any) => {
    try {
        const response = await axios.patch(
            "user/noteCover/NoteCover",
            {
                "noteId": noteId,
                "noteCover": noteCover
            }
        )
        return response.data.status
    }catch (e) {
        console.error(e)
    }
}