// 控制右侧面板是否显示
import {defineStore} from "pinia";

export const useLeftPanel = defineStore('LeftPanel', {
    state: () => ({
        vis: true,  // 是否显示

    }),
    actions: {
        openVis() {
            this.vis = true;
        },

        offVis() {
            this.vis = false;
        }
    },
    persist: true,
});