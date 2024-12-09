// 用于 获取 笔记的 详细信息
import axios from "@/axios";

export const getFolderDescription = async (noteId: number) => {
    try {
        const response = await axios.get(
            "user/noteTree/folderDescription",
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