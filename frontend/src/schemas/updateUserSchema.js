// Utils
import { isBlank, equals } from "../utils/StringUtils";
import { hasLegalAge } from "../utils/DateUtils";
import { validateEmail } from "../utils/ValidationUtils";

/**
 * Schema responsavel por validar os dados de atualizacao de um usuario
 */

export const validateUpdateUser = (data) => {
    const errors = [];

    if(isBlank(data.name)) errors.push({ type: "ERROR", message: "O nome é obrigatório" });
    if(isBlank(data.birthDate)) errors.push({ type: "ERROR", message: "A data de nascimento é obrigatória" });
    if(!hasLegalAge(data.birthDate)) errors.push({ type: "ERROR", message: "A idade mínima é 18 anos" });
    if(isBlank(data.email)) errors.push({ type: "ERROR", message: "O email é obrigatório" });
    if(!isBlank(data.newPassword) && isBlank(data.currentPassword)) errors.push({ type: "ERROR", message: "A senha é obrigatória" });
    if(!equals(data.newPassword, data.confirmNewPassword)) errors.push({ type: "ERROR", message: "As senhas precisam ser iguais!" })

    if(errors.length > 0) return errors[0];

    const error = validateEmail(data.email);
    if(error) return error;

    if(isBlank(data.currentPassword)) data.currentPassword = null;
    if(isBlank(data.newPassword)) data.newPassword = null;

    return null;
}