import axios from "@/axios";

export const putFolderDescription = async (folderId: number, folderDescription: string) => {
try {
    const response = await axios.put(
        "user/noteTree/FolderDescription",
        {
            folderId: folderId,
            folderDescription: folderDescription,
        }

    )
    return response.data.status
}catch (e){
    console.error(e);
}
}