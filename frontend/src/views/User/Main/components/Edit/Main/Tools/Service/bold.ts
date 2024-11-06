// 用于 文本编辑器中的 加粗的功能
import {Editor} from "@tiptap/vue-3";

export const bold = (editor: Editor) => {
    editor.chain().focus().toggleBold().run()
}

// 检查当前文本是否已加粗
export const isBoldActive = (editor: Editor | undefined): boolean => {
    if (!editor) {
        return false; // 如果 editor 未定义，则返回 false
    }
    return editor.isActive('bold');
}