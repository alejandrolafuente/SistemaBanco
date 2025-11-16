import { Endereco } from "./endereco";
import { IEntidadeCadastravel } from "./ientidade-cadastravel";

export interface IClienteCadastravel extends IEntidadeCadastravel {
    salario: number;
    endereco: Endereco;
}
