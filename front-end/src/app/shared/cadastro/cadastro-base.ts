import { NgForm } from "@angular/forms";
import { ICadastroStrategy } from "./icadastro-strategy";
import { IEntidadeCadastravel } from "./ientidade-cadastravel";
import { LoginService } from "../../autenticacao/servicos/login.service";
import { Directive, ViewChild } from "@angular/core";

@Directive()
export abstract class CadastroBase implements ICadastroStrategy {

    @ViewChild('formCadastro')
    formCadastro!: NgForm;

    erroMensagem: string = '';
    emailMessage: string = '';
    cpfMessage: string = '';
    mostrarConfirmacao: boolean = false;
    dadosConfirmacao: any;
    //*
    //protected abstract get form(): NgForm;
    //*
    protected get form(): NgForm {
        return this.formCadastro;
    }
    
    protected abstract get entidade(): IEntidadeCadastravel;

    constructor(protected loginService: LoginService) { }

    // Método na base que todas as subclasses usarão
    gerarCPFValido(): void {
        const randomDigit = () => Math.floor(Math.random() * 10);

        // gera 9 dígitos aleatórios
        let cpf = Array.from({ length: 9 }, randomDigit);

        // calcula o primeiro dígito verificador
        let soma = cpf.reduce((acc, digit, index) => acc + digit * (10 - index), 0);
        let resto = soma % 11;
        cpf.push(resto < 2 ? 0 : 11 - resto);

        // calcula o 2o dígito verificador
        soma = cpf.reduce((acc, digit, index) => acc + digit * (11 - index), 0);
        resto = soma % 11;
        cpf.push(resto < 2 ? 0 : 11 - resto);
        this.entidade.cpf = cpf.join('');
    }

    verificarCpf(cpf: string): void {
        const cpfLimpo = cpf.replace(/\D/g, '');

        if (cpfLimpo && cpfLimpo.length === 11) {
            this.loginService.verificarCpfExistente(cpfLimpo).subscribe({
                next: (existe) => {
                    this.cpfMessage = existe ? 'CPF já cadastrado no sistema' : '';
                },
                error: (erro) => {
                    this.cpfMessage = 'Erro ao verificar CPF';
                    console.error('Erro na verificação de CPF:', erro);
                }
            });
        }
    }

    verificarEmail(email: string): void {

        if (email) {
            this.loginService.verificarEmailExistente(email).subscribe({
                next: (existe) => {
                    this.emailMessage = existe ? 'Email já cadastrado no sistema' : '';
                },
                error: (erro) => {
                    this.emailMessage = 'Erro ao verificar email';
                    console.error('Erro na verificação de email:', erro);
                }
            });
        }
    }

    public cadastrar(): void {
        if (this.validarAntesDoCadastro()) {
            this.mostrarConfirmacao = true;
            this.dadosConfirmacao = this.obterDadosConfirmacao();
            //this.processarCadastro();
        }
    }

    validarAntesDoCadastro(): boolean {
        if (!this.form.valid) {
            this.marcarCamposComoSujos();
            return false;
        }

        if (this.emailMessage || this.cpfMessage) {
            return false;
        }

        return true;
    }

    //*
    private marcarCamposComoSujos(): void {
        Object.keys(this.form.controls).forEach(key => {
            this.form.controls[key].markAsDirty();
            this.form.controls[key].markAsTouched();
        });
    }

    // template method => as subclasses implementam o processo específico
    // protected abstract processarCadastro(): void;

    obterDadosConfirmacao(): any {

        const dadosBasicos = {
            cpf: this.entidade.cpf,
            email: this.entidade.email,
            nome: this.entidade.nome,
            telefone: this.entidade.telefone
        };
        // se for Cliente (tem salario e endereco), inclui dados extras
        if ('salario' in this.entidade) {
            return {
                ...dadosBasicos,
                salario: (this.entidade as any).salario,
                endereco: (this.entidade as any).endereco,
                tipo: 'cliente'
            };

        }
        // para admin e gerente so dados basicos
        return {
            ...dadosBasicos,
            tipo: this.entidade.hasOwnProperty('nivelAcesso') ? 'administrador' : 'gerente'
        };
    }


    //*
    voltarParaEdicao(): void {
        this.mostrarConfirmacao = false;
    }


}
