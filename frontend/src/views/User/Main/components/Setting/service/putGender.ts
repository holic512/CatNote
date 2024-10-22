import axios from "../../../../../../axios";

const putGender = async (genderA: string) => {
    try {
        const response = await axios.put(
            "user/settings/account/gender",
            {
                gender: genderA,
            }
        )
        return response.data.status;
    } catch (err) {
        return 500;
    }
}
export {putGender}
