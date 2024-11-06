// 用于 文本编辑器中的 无序列表的功能
import {Editor} from "@tiptap/vue-3";

export const toggleOrderedList = (editor: Editor) => {
    editor.commands.toggleOrderedList()
    // 回到焦点
    editor.commands.focus();
}
