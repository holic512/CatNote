import axios from "../../../../../../../axios";

export const saveCompressedNoteImage = async (base64Image: any, type: string) => {
    try {
        const formData = new FormData();
        formData.append("image", base64Image);
        formData.append("type", type);

        const response = await axios.post(
            "user/noteImage/saveCompressedNoteImage",
            formData,
            {headers: {'Content-Type': 'multipart/form-data'}}
        )
        if (response.data.status === 200) {
            return response.data.data;
        } else {
            return null;
        }
    } catch {
        return 500;
    }
}