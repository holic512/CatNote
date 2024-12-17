// 用于更新 笔记 或者 文件夹的 头像
import axios from "../../../../../../../../axios";

export const updateNoteAvatar = async (noteId: number, avatar: string) => {
    try {
        const response = await axios.put(
            "user/noteTree/NoteAvatar",
            {
                noteId: noteId,
                avatar: avatar,
            }
        )
        console.log(response.data)
        return response.data.status
    } catch (e) {
        console.error(e)
    }
}