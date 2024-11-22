import {useEditor} from "@tiptap/vue-3";
import StarterKit from "@tiptap/starter-kit";
import Underline from "@tiptap/extension-underline";
import TextStyle from "@tiptap/extension-text-style";
import Placeholder from "@tiptap/extension-placeholder";
import {Color} from "@tiptap/extension-color";
import Highlight from "@tiptap/extension-highlight";
import Image from "@tiptap/extension-image";
import FileHandler from "@tiptap-pro/extension-file-handler";
import fileHandlerConfig from "./fileHandlerConfig.ts";
import NodeRange from "@tiptap-pro/extension-node-range";
import DragHandle from "@tiptap-pro/extension-drag-handle";
import {useSaveNoteState} from "../Pinia/SaveNoteState.ts";
import {TaskItem} from "@tiptap/extension-task-item";
import {TaskList} from "@tiptap/extension-task-list";
import {Table} from "@tiptap/extension-table";
import {TableRow} from "@tiptap/extension-table-row";
import {TableHeader} from "@tiptap/extension-table-header";
import {TableCell} from "@tiptap/extension-table-cell";

export function createEditorInstance() {
    const editorSaveState = useSaveNoteState();

    const editor = useEditor({
        extensions: [

            StarterKit,

            // 启用下划线扩展
            Underline,

            // 文本样式
            TextStyle,

            Placeholder.configure({
                placeholder: '创造你的无限可能',
            }),

            // 颜色
            Color,

            // 高亮
            Highlight.configure({
                multicolor: true,
            }),

            // // 目录
            // TableOfContents.configure({
            //     getIndex: getHierarchicalIndexes,
            //     onUpdate(content) {
            //         // 在这里更新目录项
            //         items.value = content;
            //     },
            // }),

            // 图片
            Image,

            // 推拽 粘贴
            FileHandler.configure(fileHandlerConfig),

            // node
            NodeRange.configure({
                // allow to select only on depth 0
                // depth: 0,
                key: null,
            }),

            // 拖拽区块
            DragHandle.configure({
                render() {
                    const element = document.createElement('div')

                    element.classList.add('custom-drag-handle')

                    return element
                },
            }),

            // 任务清单
            TaskList,
            TaskItem.configure({
                nested: true,
            }),

            // 表格
            Table.configure({
                resizable: true,
            }),
            TableRow,
            TableHeader,
            TableCell,




        ],

        content: "",

// 监听粘贴事件
        onPaste: (e, slice) => {
            // 取消默认粘贴行为
            e.preventDefault();

            // 获取剪贴板中的纯文本内容
            const text = slice.content.textBetween(0, slice.content.size, '\n', '\n');

            console.log(text);

            // 通过正则去掉文本中的 HTML 标签
            const cleanText = text.replace(/<\/?[^>]+(>|$)/g, ""); // 去除所有HTML标签

            // 创建一个新的事务，替换选区中的内容
            editor.value?.view.state.tr.replaceSelectionWith(
                editor.value?.view.state.schema.text(cleanText) // 用清除样式的文本替换
            );

        },


// 监听 editorContent 的变化
        onUpdate: ({}) => {
            // 更新内容状态，触发保存或其他处理
            editorSaveState.updateContent()
        }

    })

    return editor
}