import { NgForm } from "@angular/forms";

export interface ICadastroStrategy {

    formCadastro: NgForm;

    erroMensagem: string;
    emailMessage: string;
    cpfMessage: string;
    mostrarConfirmacao: boolean

    gerarCPFValido(): void

    verificarCpf(cpf: string): void;

    verificarEmail(email: string): void;

    validarAntesDoCadastro(): boolean;

    // metodo para obter dados de confirmação
    obterDadosConfirmacao(): any;
}
