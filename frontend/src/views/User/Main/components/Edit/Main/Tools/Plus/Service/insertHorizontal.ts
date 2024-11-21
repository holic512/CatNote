// insertHorizontal.ts
// 用途: 在 编辑器中插入 分解线
import {Editor} from '@tiptap/vue-3'

export function insertHorizontal(editor: Editor) {
    editor.commands.setHorizontalRule()

}
