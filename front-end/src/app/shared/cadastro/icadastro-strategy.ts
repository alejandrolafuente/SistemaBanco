import { NgForm } from "@angular/forms";

export interface ICadastroStrategy {

    emailMessage: string;
    cpfMessage: string;

    gerarCPF(): string;

    verificarCpf(cpf: string): void;

    verificarEmail(email: string): void;

    validarAntesDoCadastro(): boolean;

    // metodo para obter dados de confirmação
    obterDadosConfirmacao(): any;
}
