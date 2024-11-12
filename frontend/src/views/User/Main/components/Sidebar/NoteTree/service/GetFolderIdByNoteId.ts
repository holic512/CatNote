// 获取 此 data的类型 并溯源到 目录文件夹
import {Tree} from "./treeInterface.ts";
import axios from "../../../../../../../axios";

const getFolderIdByNoteId = async (data: Tree) => {
    // 当为空的时候 直接返回 null
    if (data == null) return null;

    // 当他为文件夹的时候 直接返回文件夹的 id
    if (data.type == "FOLDER") return data.id;

    // 当点击的是文件的时候 返回此文件的父级文件夹id
    try {
        const response = await axios.get(
            "user/noteTree/folderIdByNoteId",
            {
                params: {
                    noteId: data.id
                }
            }
        )
        return response.data.data;
    } catch (err) {
        console.error(err);
    }
}
export {getFolderIdByNoteId}