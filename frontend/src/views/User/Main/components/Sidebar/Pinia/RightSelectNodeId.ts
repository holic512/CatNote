// 当前笔记的选中Id 用于 右键菜单后续的 添加,编辑等一系列操作
import {defineStore} from 'pinia'
import {Tree} from "../NoteTree/interface/treeInterface";

export const useRightSelectNodeId = defineStore('RightSelectNodeId', {
    state() {
        return {
            // 选中的 id
            selectNodeId: 0,

            // 选中的数据
            data: {},

            // 用于重命名 动态显示的 位置
            selectX: 0,
            selectY: 0,
        }
    },

    actions: {

        SetSelectNodeId(data: number): void {
            this.selectNodeId = data
        },

        setSelectData(data: Tree): void {
            this.data = data
        },

        setSelectPos(x: number, y: number): void {
            this.selectX = x
            this.selectY = y
        },

        GetSelectNodeId(): number {
            return this.selectNodeId
        }
    },

})
