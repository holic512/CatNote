// 用于文本编辑器中的删除线功能
import {Editor} from "@tiptap/vue-3";

export const strike = (editor: Editor) => {
    editor.chain().focus().toggleStrike().run();
}

//  检查删除线状态
export const isStrikeActive = (editor: Editor ): boolean => {
    if (!editor) {
        return false; // 如果 editor 未定义，则返回 false
    }
    return editor.isActive('strike');
};