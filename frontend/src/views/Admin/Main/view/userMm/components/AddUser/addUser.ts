import axios from "../../../../../../../axios";
import {Ref} from "vue";

export interface AddUserForm {
    username: string;
    email: string;
    confirmPassword: string;
    password: string;
    status: string;
}

const addUser = async (form: Ref<AddUserForm>) => {
    try {
        const response = await axios.post(
            "/admin/userMm/addUser",
            {
                "username": form.value.username,
                "password": form.value.password,
                "email": form.value.email,
                "status": form.value.status,
            }
        );
        return response.data.status;
    } catch (error) {
        return 500;
    }
}

export {addUser}