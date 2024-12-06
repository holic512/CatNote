// 用于保存笔记目录
import {defineStore} from "pinia";
import {TableOfContentData} from "@tiptap-pro/extension-table-of-contents";

export const useIndexItemsStore = defineStore('IndexItemsStore', {
    state: () => ({
        IndexItems: [] as TableOfContentData  // 如果你要存储多个目录项，初始化为一个空数组
    }),
    actions: {},
})