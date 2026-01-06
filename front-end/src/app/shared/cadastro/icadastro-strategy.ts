import { NgForm } from "@angular/forms";

export interface ICadastroStrategy {

    // propriedades
    formCadastro: NgForm;
    erroMensagem: string;
    emailMessage: string;
    cpfMessage: string;
    mostrarConfirmacao: boolean
    dadosConfirmacao: any;


    // metodos do fluxo de cadastro (em ordem logica)
    gerarCPFValido(): void

    verificarCpf(cpf: string): void;

    verificarEmail(email: string): void;

    validarAntesDoCadastro(): boolean;

    cadastrar(): void;

    // metodo para obter dados de confirmação
    obterDadosConfirmacao(): any;

    // template method 
    confirmarEnvio(): void;

    
}
