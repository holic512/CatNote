// 用于 文本编辑器中的 撤销的功能
import {Editor} from "@tiptap/vue-3";

export const undo = (editor: Editor) => {
    editor.commands.undo()
}
