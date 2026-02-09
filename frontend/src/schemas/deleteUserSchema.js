// Utils
import { isBlank, notEquals } from "../utils/StringUtils";

/**
 * Schema responsavel por validar os dados de exclusao de um usuario
 */

export const validateDelete = (data) => {
    const errors = [];

    if(isBlank(data.admin.password)) errors.push({ type: "ERROR", message: "A senha é obrigatória" });
    if(isBlank(data.admin.confirmPassword)) errors.push({ type: "ERROR", message: "A confirmação de senha é obrigatória" });
    if(notEquals(data.admin.password, data.admin.confirmPassword)) errors.push({ type: "ERROR", message: "As senhas precisam ser iguais" });
    if(isBlank(data.admin.confirm)) errors.push({ type: "ERROR", message: "A confirmação é obrigatória" });
    if(notEquals(data.admin.confirm, "Confirmar")) errors.push({ type: "ERROR", message: "Confirme a exclusão antes de continuar" });

    if(errors.length > 0) return errors[0];

    return null;
}