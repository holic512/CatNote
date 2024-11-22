// 用于 新建笔记 操作

import axios from "../../../../../../../axios";
import {useNoteTreeUpdate} from "../../Pinia/isNoteTreeUpdated";

const AddFolder = async (FolderId: number) => {

    // 传入id 新建笔记
    try {
        const response = await axios.post(
            "user/noteTree/folder",
            {
                FolderId: FolderId
            }
        )

        // 当状态码 返回 200 则更新笔记
        if (response.data.status === 200) {
            const isNoteTreeUpdated = useNoteTreeUpdate();
            isNoteTreeUpdated.UpdatedNoteTree();
        }

    } catch (err) {
        return 500;
    }
}

export {AddFolder}