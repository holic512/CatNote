// 获取笔记信息 更准确的来说 是用于根据现有目录树来 设置笔记信息
import {useCurrentNoteInfoStore} from "../../../Edit/Pinia/currentNoteInfo.ts";
import type Node from "element-plus/es/components/tree/src/model/node";

export const getNoteInfo = async (node: Node) => {
    // 获取笔记信息 并 跳转到笔记路由 - 异步
    // 获取 文章 目录
    const parents: string[] = [];
    let currentNode = node;
    while (currentNode.parent.data.label) {
        parents.unshift(currentNode.parent.data.label)
        currentNode = currentNode.parent;
    }
    // 存储到 pinia中
    const currentNoteInfo = useCurrentNoteInfoStore()
    currentNoteInfo.setNoteInfo(node.data.id, node.data.label, parents)
}