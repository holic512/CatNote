import axios from "../../../../../../../../axios";

export const updateFolderTitle = async (folderId: number, folderTitle: string) => {
    try {
        const response = await axios.put(
            "user/noteTree/FolderTitle",
            {
                folderId: folderId,
                folderTitle: folderTitle,
            }
        )
        return response.data.status
    } catch (e) {
        console.error(e)
    }
}