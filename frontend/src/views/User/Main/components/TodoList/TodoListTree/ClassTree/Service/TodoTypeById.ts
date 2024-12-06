export const TodoTypeById = (type: number) => {
    switch (type) {
        case 0 :
            return 'primary';
        case 1 :
            return 'info';
        case 2 :
            return 'success';
        case 3 :
            return 'warning';
        case 4 :
            return 'danger';
        default:
            return 'info';
    }
}