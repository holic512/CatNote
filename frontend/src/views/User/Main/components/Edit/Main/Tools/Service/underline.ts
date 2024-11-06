// 用于文本编辑器中的下划线功能
import {Editor} from "@tiptap/vue-3";

export const underline = (editor: Editor) => {
    editor.chain().focus().toggleUnderline().run();
};

// 检查当前文本是否已下划线
export const isUnderlineActive = (editor: Editor | undefined): boolean => {
    if (!editor) {
        return false; // 如果 editor 未定义，则返回 false
    }
    return editor.isActive('underline');
}
