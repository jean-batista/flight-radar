// Hooks
import { createContext, useContext, useEffect, useState } from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {

    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    // Verifica se possui um usuario autenticado
    useEffect(() => {
        const username = localStorage.getItem("username");
        const token = localStorage.getItem("token");
        const roles = localStorage.getItem("roles");

        if(username !== null && token !== null && roles !== null) {
            setUser({ username, token, roles: [...roles.split(",")] });
        }

        setLoading(false);
    }, []);

    // Salva as informacoes do usuario no localStorage
    useEffect(() => {
        if(user !== null) {
            localStorage.setItem("username", user.username);
            localStorage.setItem("token", user.token);
            localStorage.setItem("roles", user.roles);
        }
    }, [user]);

    return (
        <AuthContext.Provider value={{ user, setUser, loading, setLoading }}>
            {children}
        </AuthContext.Provider>
    )
}

// Retorna o usuario do contexto
export const useAuthValue = () => {
    return useContext(AuthContext);
}