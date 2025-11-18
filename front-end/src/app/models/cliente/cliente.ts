import { Endereco } from "../endereco/endereco";

export interface Cliente {
    cpf: string;
    email: string;
    nome: string;
    telefone: string;
    salario: number | null;
    endereco: Endereco
}
