import {Tree} from "./treeInterface.ts";
import {getFolderIdByNoteId} from "./GetFolderIdByNoteId.ts";
import axios from "../../../../../../../axios";

const AddFolder = async (data: Tree) => {
    // 获取 新建笔记的位置
    const FolderId = await getFolderIdByNoteId(data);

    // 传入id 新建笔记
    try {
        const response = await axios.post(
            "user/noteTree/folder",
            {
                FolderId: FolderId
            }
        )
        return response.data.status;
    } catch (err) {
        return 500;
    }
}

export {AddFolder}