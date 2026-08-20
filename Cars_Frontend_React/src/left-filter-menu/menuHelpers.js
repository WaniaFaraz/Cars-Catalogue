//helper functions for the LeftFilterMenuCategory

export const formatPkr = (rawNum) => {
    const num = parseInt(rawNum, 10);
    if (isNaN(num)) return "PKR 0";
    if (num >= 1000000000) return `PKR ${(num / 1000000000).toFixed(1)}B`;
    if (num >= 1000000) return `PKR ${(num / 1000000).toFixed(1)} M`;
    if (num >= 1000) return `PKR ${(num / 1000).toFixed(1)} K`;
    return `PKR ${num}`;

}