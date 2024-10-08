// 获取用户身份状态 所代表的 样式和信息
const getStatusType = (status: string) => {
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
const getStatusMsg = (status: string) => {
    switch (status) {
        case 'ACTIVE':
            return '正常';
        case 'DISABLED':
            return '停用';
        case 'BANNED':
            return '封禁';
        default:
            return '未知';  // 添加一个默认值
    }
}


export {getStatusType,getStatusMsg};