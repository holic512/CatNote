// 用于更新 笔记 或者 文件夹的 头像
import axios from "../../../../../../../../axios";

export const updateFolderAvatar = async (folderId: number, avatar: string) => {
    try {
        const response = await axios.put(
            "user/noteTree/FolderAvatar",
            {
                folderId: folderId,
                avatar: avatar,
            }
        )
        return response.data.status
    } catch (e) {
        console.error(e)
    }
}