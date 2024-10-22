import axios from "../../../../../../axios";

const putBio = async (bioA: string) => {
    try {
        const response = await axios.put(
            "user/settings/account/bio",
            {
                bio: bioA
            }
        )

        return response.data.status;
    } catch (err) {
        return 500;
    }

}

export {putBio}