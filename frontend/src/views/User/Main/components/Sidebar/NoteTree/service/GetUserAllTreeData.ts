// 用于 获取该用户的 全部树结构
import axios from "../../../../../../../axios";

export const getUserAllTreeData = async () => {
    try {
        const response = await axios.get(
            "user/noteTree/UserAll",
        );

        // 将数据添加唯一字段 type+id
        let data = response.data.data;

        // 遍历数据并为每个节点添加 uniqueId
        const addUniqueId = (nodes:any) => {
            return nodes.map((node:any) => {
                // 添加唯一的 uniqueId，格式为 type+id
                node.uniqueId = `${node.type}_${node.id}`;

                // 如果有子节点，则递归处理
                if (node.children) {
                    node.children = addUniqueId(node.children);
                }

                return node;
            });
        };

        // 返回处理后的数据
        return addUniqueId(data);

    } catch (e) {
        return null;
    }
}