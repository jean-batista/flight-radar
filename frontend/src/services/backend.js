// Axios
import axios from "axios";

/**
 * Configuracoes do axios e do backend
 */

const backend = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 10000,
});

export default backend;