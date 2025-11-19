import { NgForm } from "@angular/forms";

export interface ICadastroStrategy {

    emailMessage: string;
    cpfMessage: string;

    gerarCPF(): string;

    verificarCpf(cpf: string): void;

    verificarEmail(email: string, form: NgForm, campo: string): void;

    validarAntesDoCadastro(): boolean;

    // metodo para obter dados de confirmação
    obterDadosConfirmacao(): any;
}
