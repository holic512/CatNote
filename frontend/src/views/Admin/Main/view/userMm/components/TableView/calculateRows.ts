const calculateRows = (minHeight: number, stepHeight: number): number => {
    // 获取屏幕高度
    const height = window.innerHeight;
    // 如果高度小于等于 720px，显示 10 条
    if (height <= minHeight) {
        return 10;
    }

    // 根据高度动态增加显示的条数
    const additionalRows = Math.ceil((height - minHeight) / stepHeight);
    return additionalRows + 10;  // 11是基础行数+1
}
export {calculateRows};