export class Usuario {
    constructor(
        public id?: number,
        public token?: string,
        public nome?: string,
        public email?: string,
        public cpf?: string,
        public telefone?: string,
        public perfil?: string
    ) { }
}
