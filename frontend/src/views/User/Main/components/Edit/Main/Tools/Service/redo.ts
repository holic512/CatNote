// 用于 文本编辑器中的 重做的功能
import {Editor} from "@tiptap/vue-3";

export const redo = (editor: Editor) => {
    editor.commands.redo()
}
