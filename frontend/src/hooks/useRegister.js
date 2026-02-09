// Hooks
import { useState } from "react";

// Schemas
import { validateRegister } from "../schemas/registerSchema";

// Axios
import { useAxios } from "./useAxios";

/**
 * Hook responsavel pela logica de cadastro
 */

export const useRegister = () => {

    const [info, setInfo] = useState(null);
    const { request, loading } = useAxios();

    const register = async ({ name, birthDate, email, password, confirmPassword }) => {
        setInfo(null);

        const data = {
            name,
            birthDate,
            email,
            password,
            confirmPassword
        }

        const error = validateRegister(data);

        if(error) {
            setInfo(error);
            setTimeout(() => { setInfo(null); }, 2000);
            return;
        }

        const response = await request("BACKEND", "/auth/register", "POST", data);
        
        if(response.error) {
            setInfo(response.error);
            setTimeout(() => { setInfo(null); }, 2000);
            return;
        }
        
        setInfo({ type: "SUCCESS", message: "Usuário cadastrado com sucesso" })
        setTimeout(() => { setInfo(null); }, 2000);
        
    }

    return { register, info, loading };
}