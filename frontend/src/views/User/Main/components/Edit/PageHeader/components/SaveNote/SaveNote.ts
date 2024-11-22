import {Editor} from "@tiptap/vue-3";
import {useCurrentNoteInfoStore} from "../../../Pinia/currentNoteInfo";
import axios from "../../../../../../../../axios";
import {ElMessage} from "element-plus";
import {useSaveNoteState} from "../../../Pinia/SaveNoteState";

export const SaveNote = async (editor: Editor) => {

    // 笔记信息 pinia 实例
    const currentNoteInfo = useCurrentNoteInfoStore()
    try {
        // 获取 编辑器中的 笔记json
        const NoteDataJson = editor.getJSON()
        const contentString = JSON.stringify(NoteDataJson);

        // 获取 当前笔记的 ID
        const NoteId = currentNoteInfo.noteId;

        // 调用保存功能
        const response = await axios.post(
            "user/note/SaveNote",
            {
                "noteId": NoteId,
                "content": contentString
            });

        // 状态反馈
        if (response.data.status == 200) {
            // 恢复 保存状态
            const SaveNoteState = useSaveNoteState();
            SaveNoteState.saveContent();

            ElMessage.success("笔记保存成功");
        } else
            ElMessage.error("笔记保存失败");

    } catch (e) {
        console.error(e);
    }
}