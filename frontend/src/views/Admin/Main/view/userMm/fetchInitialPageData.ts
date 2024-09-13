import axios from "../../../../../axios";

const fetchInitialPageData = async (nowRow: number) => {
    const response = await axios.get(
        "admin/userMm/fetchInitialUser", {
            params: {
                count: nowRow
            }
        }
    )
    return response.data.data;
}
export default fetchInitialPageData;