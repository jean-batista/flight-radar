// Utils
import { isBlank } from "../utils/StringUtils";
import { validateEmail } from "../utils/ValidationUtils";

/**
 * Schema responsavel por validar os dados de atualizacao de um usuario
 * Este Schema e referente a atualizao realizada por um usuario administrador
 */

export const validateUpdate = (data) => {

    const errors = [];

    if(isBlank(data.name)) errors.push({ type: "ERROR", message: "O nome é obrigatório" });
    if(isBlank(data.email)) errors.push({ type: "ERROR", message: "O email é obrigatório" });
    
    if(errors.length > 0) return errors[0];
    
    const error = validateEmail(data.email);

    if(error) return error;

    return null;

}