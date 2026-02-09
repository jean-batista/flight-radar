// Hooks
import { useState } from "react";

// Services
import backend from "../services/backend";
import api from "../services/api";

/**
 * Hook responsavel por fazer as requisicoes http via axios
 * A requisicao e configurada de acordo com os parametros do metodo request
 */

export const useAxios = (token = null) => {

    const [loading, setLoading] = useState(null);

    const request = async(destiny = null, url, method = "GET", data = null, params = null) => {
        setLoading(true);

        let axios = null;

        switch(destiny) {
            case "API":
                axios = api;
                break;
            case "BACKEND":
                axios = backend;
                break;
            default:
                setLoading(false);
                return { status: null, data: null, error: { type: "ERROR", message: "Requisição inválida" } };
        }

        const config = {
            method,
            url,
            data,
            params,
            headers: {
                "Content-Type": "application/json",
                "Authorization": token ? `Bearer ${token}` : ""
            }
        }

        return await axios(config)
        .then((response) => {
            return { status: response.status, data: response.data, error: null };
        })
        .catch((error) => {
            return { status: error.status, data: null, error: { type: "ERROR", message: error.response ? error.response.data.message : error.message } };
        })
        .finally(() => {
            setLoading(false);
        });

    }
    
    return { request, loading };
}