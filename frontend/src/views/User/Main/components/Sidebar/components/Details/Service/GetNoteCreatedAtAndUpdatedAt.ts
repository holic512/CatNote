// 用于 获取 笔记的 创建时间 和 更新时间
import axios from "@/axios";

export const GetNoteCreatedAtAndUpdatedAt = async (noteId: number) => {
    try {
        const response = await axios.get(
            "user/noteTree/NoteCreatedAtAndUpdatedAt",
            {
                params: {
                    noteId: noteId
                }
            }
        )
        return response.data.data;
    }catch (e) {
        console.error(e);
    }
}