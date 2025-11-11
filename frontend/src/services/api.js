// Axios
import axios from "axios";

/**
 * Configuracoes do axios e da api
 */

const api = axios.create({
    baseURL: "http://localhost:8090",
    timeout: 10000,
});

export default api;