import axios from "../../../../../../../axios";


const getNoteTree = async (parentId: number | null) => {
    try {
        const response = await axios.get(
            "user/noteTree/noteTree",
            {
                params: {
                    parentId: parentId
                }
            }
        );
        return response.data.data;
    } catch (error) {
        return 500;
    }
}

export {getNoteTree}