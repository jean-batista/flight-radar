// Context
import { useAuthValue } from "../context/AuthContext";

/**
 * Hook responsavel pela logica de logout
 */

export const useLogout = () => {

    const { setUser } = useAuthValue();

    const logout = () => {
        localStorage.clear();
        setUser(null);
    }

    return { logout };

}