// 获取用户身份状态 所代表的 样式和信息
const getStatusType = (status) => {
    switch (status) {
        case 0:
            return 'success';
        case 1:
            return 'info';
        case 2:
            return 'warn';
        default:
            return 'default';  // 添加一个默认值
    }
}
const getStatusMsg = (status) => {
    switch (status) {
        case 0:
            return '正常';
        case 1:
            return '停用';
        case 2:
            return '封禁';
        default:
            return '未知';  // 添加一个默认值
    }
}


export {getStatusType,getStatusMsg};