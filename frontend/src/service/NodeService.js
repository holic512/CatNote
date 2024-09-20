// src/service/NodeService.js
const nodes = [
    {
        key: '0',
        label: '文件夹1',
        children: [
            { key: '0-0', label: '文件1' },
            { key: '0-1', label: '文件2' },
        ],
    },
    {
        key: '1',
        label: '文件夹2',
        children: [],
    },
];

export const NodeService = {
    getTreeNodes() {
        return new Promise((resolve) => {
            setTimeout(() => {
                resolve(nodes);
            }, 500); // 模拟延迟
        });
    },
};
