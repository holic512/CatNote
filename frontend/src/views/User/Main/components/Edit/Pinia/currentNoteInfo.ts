// stores/currentNoteInfo.ts
import {defineStore} from 'pinia';

interface CurrentNoteInfo {
    noteId: number | null;
    noteLocation: string[];
    noteName: string;
    avatar: string;
}

export const useCurrentNoteInfoStore = defineStore('currentNoteInfo', {
    state: (): CurrentNoteInfo => ({
        noteId: null,  // 笔记ID
        noteName: '',  // 笔记名称
        noteLocation: [], // 树结构
        avatar: '', // 笔记头像
    }),
    actions: {
        setNoteInfo(id: number, name: string, location: string[], avatar: string) {
            this.noteId = id;
            this.noteLocation = location;
            this.noteName = name;
            this.avatar = avatar;

        },
        clearNoteInfo() {
            this.noteId = null;
            this.noteLocation = [];
            this.noteName = '';
        }
    },
    persist: true,
});
