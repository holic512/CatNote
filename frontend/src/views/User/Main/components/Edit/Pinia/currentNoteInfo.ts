// stores/currentNoteInfo.ts
import {defineStore} from 'pinia';

interface CurrentNoteInfo {
    // 笔记ID
    noteId: number | null;
    // 树结构
    noteLocation: string[];
    // 笔记名称
    noteName: string;
    // 笔记头像
    avatar: string;
    // 笔记背景
    cover: string | null;
}

export const useCurrentNoteInfoStore = defineStore('currentNoteInfo', {
    state: (): CurrentNoteInfo => ({
        noteId: null,
        noteName: '',
        noteLocation: [],
        avatar: '',
        cover: null,
    }),
    actions: {
        setNoteInfo(id: number, name: string, location: string[], avatar: string, cover: string | null) {
            this.noteId = id;
            this.noteLocation = location;
            this.noteName = name;
            this.avatar = avatar;
            this.cover = cover;
        },
        clearNoteInfo() {
            this.noteId = null;
            this.noteLocation = [];
            this.noteName = '';
            this.avatar = '';
            this.cover = null;
        }
    },
    persist: true,
});
