import { Endereco } from "../endereco/endereco.model";

export class ClienteCadastrado {
    constructor(
        public id?: number,
        public cpf?: string,
        public email?: string,
        public nome?: string,
        public telefone?: string,
        public salario?: number,
        public endereco?: Endereco
    ) { }
}
