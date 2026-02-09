// Hooks
import { useState } from "react";

// Axios
import { useAxios } from "../hooks/useAxios";

// Auth
import { useAuthValue } from "../context/AuthContext";

// Schema
import { validateDelete } from "../schemas/deleteUserSchema";

/**
 * Hook responsavel pela logica de exclusao de um usuario
 */

export const useDeleteUser = () => {

    const [info, setInfo] = useState(null);
    const [loading, setLoading] = useState(false);
    const { user } = useAuthValue();
    const { request } = useAxios(user?.token);

    const deleteUser = async (data) => {
        setLoading(true);

        const error = validateDelete(data);

        if(error) {
            setInfo(error);
            setLoading(false);
            setTimeout(() => { setInfo(null); }, 2000);
            return;
        }

        const response = request("BACKEND", "/api/admin/v1", "DELETE",  {
            userId: data.userId,
            password: data.admin.password
        });

        if(response.error) {
            setInfo({ type: "ERROR", message: error.response.data.message || "Ocorreu uma falha, por favor tente mais tarde" });
            setTimeout(() => { setInfo(null); }, 2000);
            return;
        }
        
        setInfo({ type: "SUCCESS", message: "Usuário excluido com sucesso" });
        setTimeout(() => { setInfo(null); }, 2000);
    }

    return { deleteUser, info, loading };

}