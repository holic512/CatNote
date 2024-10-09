import axios from "../../../../../../../axios";

export const kickout = async (uid: number): Promise<number> => {
    try {
        const response = await axios.post(
            "admin/oUserMm/kickout",
            {
                uid: uid
            }
        )
        return response.status
    } catch (err) {
        return 500
    }
}