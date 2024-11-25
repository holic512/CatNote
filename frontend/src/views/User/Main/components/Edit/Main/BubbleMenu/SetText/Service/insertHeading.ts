// InsertHeading.ts
// 用途: 在 编辑器中插入 1-6 级标题
import {Editor} from '@tiptap/vue-3'

export function insertHeading(editor: Editor, level: any) {
    if (!editor || level < 1 || level > 6) return // 限制标题级别在 1-6

    editor.chain().focus().toggleHeading({level: level}).run()
}
