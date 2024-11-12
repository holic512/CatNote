// stores/currentNoteInfo.ts
import {defineStore} from 'pinia';

interface CurrentNoteInfo {
    noteId: number | null;
    noteLocation: string[];
    noteName: string;
}

export const useCurrentNoteInfoStore = defineStore('currentNoteInfo', {
    state: (): CurrentNoteInfo => ({
        noteId: null,
        noteLocation: [],
        noteName: ''
    }),
    actions: {
        setNoteInfo(id: number, name: string, location: string[]) {
            this.noteId = id;
            this.noteLocation = location;
            this.noteName = name;
        },
        clearNoteInfo() {
            this.noteId = null;
            this.noteLocation = [];
            this.noteName = '';
        }
    },
    persist: true,
});
