// Hooks
import { useState } from "react";

// Auth
import { useAuthValue } from "../context/AuthContext";

// Schema
import { validateUpdate } from "../schemas/updateUserByAdminSchema";
import { useAxios } from "./useAxios";

/**
 * Hook responsavel pela atualizacao do usuario
 * Essa operacao e realizada por um usuario administrador
 */

export const useUpdateUserByAdmin = () => {

    const [info, setInfo] = useState(null);
    const { user } = useAuthValue();
    const { request, loading } = useAxios(user?.token);

    const update = async ({ userId, name, email }) => {
        setInfo(false);

        const data = { userId, name, email };
        
        const error = validateUpdate(data);

        if(error) {
            setInfo(error);
            setTimeout(() => { setInfo(null); }, 2000);
            return;
        }

        const profile = {
            userId: data.userId,
            name: data.name,
            email: data.email
        }

        const response = await request("BACKEND", "/api/admin/v1", "PUT", profile);

        if(response.error) {
            setInfo(response.error);
            setTimeout(() => { setInfo(null); }, 2000);
            return;
        }
        
        setInfo({ type: "SUCCESS", message: "Dados atualizados com sucesso!" });
        setTimeout(() => { setInfo(null); }, 2000);
        
    }

    return { update, info, loading };

}