// Utils
import { isBlank, notEquals } from "../utils/StringUtils";
import { hasLegalAge } from "../utils/DateUtils";
import { validateEmail } from "../utils/ValidationUtils";

/**
 * Schema responsavel por validar os dados de cadastro de um usuario
 */

export const validateRegister = (data) => {
    const errors = [];

    if(isBlank(data.name)) errors.push({ type: "ERROR", message: "O nome é obrigatório" });
    if(isBlank(data.birthDate)) errors.push({ type: "ERROR", message: "A data de nascimento é obrigatória" });
    if(!hasLegalAge(data.birthDate)) errors.push({ type: "ERROR", message: "Para criar uma conta é necessário possuir mais de 18 anos" });
    if(isBlank(data.email)) errors.push({ type: "ERROR", message: "O email é obrigatório" });
    if(isBlank(data.password)) errors.push({ type: "ERROR", message: "A senha é obrigatória" });
    if(isBlank(data.confirmPassword)) errors.push({ type: "ERROR", message: "A confirmação de senha é obrigatória" });
    if(notEquals(data.password, data.confirmPassword)) errors.push({ type: "ERROR", message: "As senhas precisam ser iguais" });

    if(errors.length > 0) return errors[0];

    const error = validateEmail(data.email);
    if(error) return error;

    return null;
}