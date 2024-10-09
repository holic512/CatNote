import axios from "../../../../../../../axios";


export const logout = async (uid: number): Promise<number> => {
    try {
        const response = await axios.post(
            "admin/oUserMm/logout",
            {
                uid: uid
            }
        )
        return response.status;
    } catch (err) {
        console.error(err);
        return 500
    }
}