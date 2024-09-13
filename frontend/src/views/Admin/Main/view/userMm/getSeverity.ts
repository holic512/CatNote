// 获取用户身份状态
const getSeverity = (status: string) => {
    switch (status) {
        case 'ACTIVE':
            return 'success';
        case 'DISABLED':
            return 'info';
        case 'BANNED':
            return 'warn';
        default:
            return 'default';  // 添加一个默认值
    }
}
export { getSeverity };