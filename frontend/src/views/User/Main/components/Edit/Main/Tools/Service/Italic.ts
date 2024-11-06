// 用于文本编辑器中的斜体功能
import {Editor} from "@tiptap/vue-3";

export const italic = (editor: Editor) => {
    editor.chain().focus().toggleItalic().run();
}

// 检查当前文本是否已斜体
export const isItalicActive = (editor: Editor | undefined): boolean => {
    if (!editor) {
        return false; // 如果 editor 未定义，则返回 false
    }
    return editor.isActive('italic');
}

