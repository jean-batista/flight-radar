// Hooks
import { useState } from "react";

// Axios
import { useAxios } from "./useAxios";

// Context
import { useAuthValue } from "../context/AuthContext";

// Schema
import { validateUpdateUser } from "../schemas/updateUserSchema";

/**
 * Hook responsavel pela logica de atualizacao do usuario
 */

export const useUpdateUser = () => {

    const [info, setInfo] = useState(null);
    const { user } = useAuthValue();
    const { request, loading } = useAxios(user?.token);

    const update = async ({ name, birthDate, email, currentPassword, newPassword, confirmNewPassword }) => {
        setInfo(null);

        const data = {
            name,
            birthDate,
            email,
            currentPassword,
            newPassword,
            confirmNewPassword
        }

        const error = validateUpdateUser(data);

        if(error) {
            setInfo(error);
            setTimeout(() => { setInfo(null); }, 2000);
            return;
        }

        const response = await request("BACKEND", "/api/users/v1", "PUT", data);

        if(response.error) {
            setInfo(response.error);
            setTimeout(() => { setInfo(null); }, 2000);
            return;
        }

        if(response.data.token) localStorage.setItem("token", response.data.token);

        setInfo({ type: "SUCCESS", message: "Dados atualizados com sucesso" });
        setTimeout(() => { setInfo(null); }, 2000);
    }

    return { update, info, loading }
    
}