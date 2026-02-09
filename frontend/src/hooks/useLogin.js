// Hooks
import { useState } from "react";

// Axios
import { useAxios } from "./useAxios";

// Context
import { useAuthValue } from "../context/AuthContext";

// Schema
import { validateLogin } from "../schemas/loginSchema";

/**
 * Hook responsavel pela logica de login
 */

export const useLogin = () => {

    const [info, setInfo] = useState(null);
    const { setUser } = useAuthValue();
    const { request, loading } = useAxios();

    const login = async ({ email, password }) => {
        setInfo(null);

        const data = {
            username: email,
            password
        }

        const error = validateLogin(data);

        if(error) {
            setInfo(error);
            setTimeout(() => { setInfo(null); }, 2000);
            return;
        }

        const response = await request("BACKEND", "/auth/signin", "POST", data);
        if(response.error) {
            setInfo(response.error);
            setTimeout(() => { setInfo(null); }, 2000);
            return;
        }

        setUser({
            username: response.data.username,
            token: response.data.token,
            roles: response.data.roles
        });
    }

    return { login, info, loading };
}
