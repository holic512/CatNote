import axios from "../../../../../../axios";

const putContactInfo = async (contactInfoA: string) => {
    try {
        const response = await axios.put(
            "user/settings/account/contactInfo",
            {
                contactInfo: contactInfoA
            }
        )

        return response.data.status;
    } catch (err) {
        return 500;
    }

}

export {putContactInfo}