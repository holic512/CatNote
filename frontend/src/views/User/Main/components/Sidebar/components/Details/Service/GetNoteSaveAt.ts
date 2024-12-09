// 用于 获取 笔记的 上次保存时间
import axios from "@/axios";

export const GetNoteSaveAt = async (noteId: number) => {
    try {
        const response = await axios.get(
            "user/noteTree/NoteSaveAt",
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