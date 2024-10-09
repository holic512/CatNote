import axios from "../../../../../../../axios";

const fetchPageData = async (page: number, rows: number) => {
    const response = await axios.post(
        "admin/oUserMm/fetchPageData",
        {
            page: page,
            rows: rows,
        }
    )
    return response.data.data;
}
export {fetchPageData}