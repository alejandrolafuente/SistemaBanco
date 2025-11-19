import { NgForm } from "@angular/forms";
import { ICadastroStrategy } from "./icadastro-strategy";
import { IEntidadeCadastravel } from "./ientidade-cadastravel";
import { LoginService } from "../../autenticacao/servicos/login.service";

export abstract class CadastroBase implements ICadastroStrategy {

    emailMessage: string = '';
    cpfMessage: string = '';

    protected abstract get form(): NgForm;
    protected abstract get entidade(): IEntidadeCadastravel;

    constructor(protected loginService: LoginService) { }

    gerarCPF(): string {
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

        return cpf.join('');
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

    private marcarCamposComoSujos(): void {
        Object.keys(this.form.controls).forEach(key => {
            this.form.controls[key].markAsDirty();
            this.form.controls[key].markAsTouched();
        });
    }


    // padrao de projeto Template - as subclasses implementam o processo específico
    protected abstract processarCadastro(): void;

    public cadastrar(): void {
        if (this.validarAntesDoCadastro()) {
            this.processarCadastro();
        }
    }

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

}
