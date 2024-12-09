import axios from "@/axios";

// 用于 获取 文件夹的 创建时间 和 更新时间
export const getFolderCreatedAtAndUpdatedAt = async (folderId: number) => {
    try {
        const response = await axios.get(
            "user/noteTree/folderCreatedAtAndUpdatedAt",
            {
                params: {
                    folderId: folderId
                }
            }
        )
        return response.data.data;
    }catch (e) {
        console.error(e);
    }
}