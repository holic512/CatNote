export const getTodayDate = (): string => {
    const today = new Date();
    const month = today.getMonth() + 1; // 月份从 0 开始，需要加 1
    const day = today.getDate();
    return `${month}月${day}日`;
};
