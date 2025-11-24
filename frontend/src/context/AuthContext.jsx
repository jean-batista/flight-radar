// Context
import { createContext, useContext, useEffect, useState } from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {

    const [user, setUser] = useState(null);
    const [loading, setLoding] = useState(true);

    // Verifica se possui um usuario autenticado
    useEffect(() => {
        const username = localStorage.getItem("username");
        const token = localStorage.getItem("token");
        const roles = localStorage.getItem("roles");

        if(username !== null && token !== null && roles !== null) {
            setUser({ username, token, roles: [...roles.split(",")] });
        }

        setLoding(false);
    }, []);

    // Salva as informacoes do usuario no localStorage
    useEffect(() => {
        if(user !== null) localStorage.setItem("username", user.username);
        if(user !== null) localStorage.setItem("token", user.token);
        if(user !== null) localStorage.setItem("roles", user.roles);
    }, [user]);

    return (
        <AuthContext.Provider value={{ user, setUser, loading }}>
            {children}
        </AuthContext.Provider>
    )
}

// Retorna o usuario do contexto
export const useAuthValue = () => {
    return useContext(AuthContext);
}