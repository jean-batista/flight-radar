// Utils
import { isBlank, hasMinLength } from "../utils/StringUtils";
import { validateEmail } from "../utils/ValidationUtils";

/**
 * Schema responsavel por validar os dados de login de um usuario
 */

export const validateLogin = (data) => {
    const errors = [];

    if(isBlank(data.username)) errors.push({ type: "ERROR", message: "O email não pode estar vazio" });
    if(isBlank(data.password)) errors.push({ type: "ERROR", message: "A senha não pode estar vazia" });
    if(!hasMinLength(data.password, 4)) errors.push({ type: "ERROR", message: "A senha precisa ter pelo menos 4 caracteres" });

    if(errors.length > 0) return errors[0];

    const error = validateEmail(data.username);

    if(error) return error;

    return null;
}