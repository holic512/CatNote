// 获取 当前选中的笔记信息
// 用于 根据当前选中的 笔记信息 显示编辑器等其他内容
import {useCurrentNoteInfoStore} from "../../../Edit/Pinia/currentNoteInfo";
import type Node from "element-plus/es/components/tree/src/model/node";

export const getCurrentNoteInfo = async (node: Node) => {
    // 获取 文章 目录
    const parents: string[] = [];
    let currentNode = node;
    while (currentNode.parent.data.label) {
        parents.unshift(currentNode.parent.data.label)
        currentNode = currentNode.parent;
    }

    // 存储到 pinia中
    const currentNoteInfo = useCurrentNoteInfoStore()
    currentNoteInfo.setNoteInfo(node.data.id, node.data.label, parents, node.data.avatar)
}