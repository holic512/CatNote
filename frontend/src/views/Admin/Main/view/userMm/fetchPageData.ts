import axios from "../../../../../axios";

const fetchPageData = async (pageSize: number, pageNum: number) => {
    const response = await axios.get(
        "admin/userMm/fetchPageData", {
            params: {
                pageSize: pageSize,
                pageNum: pageNum,
            }
        }
    )
    return response.data.data;
}
export {fetchPageData}