import axios from "../../../../../../axios";

const putAge = async (ageA: number) => {
    try {
        const response = await axios.put(
            "user/settings/account/age",
            {
                age: ageA
            }
        )
        console.log(response.data);
        return response.data.status;
    } catch (err) {
        return 500;
    }

}

export {putAge}