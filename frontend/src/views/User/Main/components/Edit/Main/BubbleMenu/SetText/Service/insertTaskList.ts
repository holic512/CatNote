// InsertTaskList.ts
// 用途: 在 编辑器中插入 待做
import {Editor} from '@tiptap/vue-3'

export function InsertTaskList(editor: Editor) {
    editor.chain().focus().toggleTaskList().run()
}
