/**
 * Utilitario responsavel por conter logicas e operacoes relacionadas a data
 */

export const isBefore = (date) => {
    const [year, month, day] = date.split("-").map(Number);
    const today = new Date();
    const before = new Date(year, month - 1, day);
    return today > before;
}

export const isAfter = (date) => {
    const [year, month, day] = date.split("-").map(Number);
    const today = new Date();
    const after = new Date(year, month - 1, day);
    return after > today;
}

export const hasLegalAge = (date) => {
    if(!date) return false;

    const [year, month, day] = date.split("-").map(Number);

    // Subtrai-se 1 do mes, pois o meses em JS comecam em 0
    const birthDate = new Date(year, month - 1, day);

    const today = new Date();
    const limit = new Date(
        today.getFullYear() - 18,
        today.getMonth(),
        today.getDate()
    );

    return birthDate <= limit;
}