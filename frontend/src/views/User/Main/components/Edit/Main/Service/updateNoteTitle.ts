// 用于更新 笔记 的 名称
import axios from "@/axios";

export const updateNoteTitle = async (noteId: number, noteTitle: string) => {
    try {
        const response = await axios.put(
            "user/noteTree/NoteTitle",
            {
                noteId: noteId,
                noteTitle: noteTitle,
            }
        )
        return response.data.status
    } catch (e) {
        console.error(e)
    }
}