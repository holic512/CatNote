import {defineStore} from 'pinia';

interface State {
    // 描述更新状态
    isDescriptionVisible: boolean

}

export const useTodoCategoryState = defineStore('TodoCategoryState', {
    state(): State {
        return {
            isDescriptionVisible: true,

        }
    },
    actions: {
        description() {
            this.isDescriptionVisible = !this.isDescriptionVisible;
        },

    },

});
