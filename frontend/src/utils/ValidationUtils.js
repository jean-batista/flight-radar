// Utils
import { isBlank, notInclude } from "./StringUtils";

/**
 * Utilitario responsavel por conter logicas e operacoes relacionadas a validacoes
 */

export const validateEmail = (email) => {
    
    if(isBlank(email)) return { type: "ERROR", message: "Email vazio" };

    const errors = [];

    if(notInclude(email, "@")) errors.push({ type: "ERROR", message: "Email inválido" });
    if(notInclude(email, ".")) errors.push({ type: "ERROR", message: "Email inválido" });

    const user = email.split("@")[0];
    const domain = email.split("@")[1];
    
    if(isBlank(user)) errors.push({ type: "ERROR", message: "Email inválido" });
    if(isBlank(domain)) errors.push({ type: "ERROR", message: "Email inválido" });  
    if(notInclude(domain, ".")) errors.push({ type: "ERROR", message: "Email inválido" });
    
    const tld = domain.substring(domain.lastIndexOf("."));

    if(isBlank(tld)) errors.push({ type: "ERROR", message: "Email inválido" });

    if(errors.length > 0) return errors[0];

    return null;

}