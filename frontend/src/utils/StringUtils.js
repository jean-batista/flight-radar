/**
 * Utilitario responsavel por conter logicas e operacoes relacionadas a strings
 */

export const isBlank = (string) => string.length === 0;
export const equals = (string1, string2) => string1 === string2;
export const equalsIgoreCase = (string1, string2) => string1.toLowerCase() === string2.toLowerCase();
export const notEquals = (string1, string2) => string1 !== string2;
export const isNull = (string) => string === null;
export const notNull = (string) => string !== null;
export const hasMinLength = (string, length) => string.length >= length;
export const hasMaxLength = (string, length) => string.length <= length;
export const indexOf = (string, substring) => string.indexOf(substring);
export const lastIndexOf = (string, substring) => string.lastIndexOf(substring);
export const includes = (string, substring) => string.includes(substring);
export const notInclude = (string, substring) => !string.includes(substring);