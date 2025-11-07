import { NgForm } from "@angular/forms";
import { LoginService } from "../../autenticacao/servicos/login.service";

export abstract class CadastroBase {
    emailMessage: string = '';
    cpfMessage: string = '';

    constructor(protected loginService: LoginService) { }

    protected verificarEmail(email: string, form: NgForm, campo: string) {

        const emailControl = form.form.get(campo);

        if (emailControl && emailControl.valid) {
            this.loginService.verificarEmailExistente(email).subscribe({
                next: (existe) => {
                    if (existe) {
                        this.emailMessage = 'Email já cadastrado no sistema';
                    } else {
                        this.emailMessage = '';
                    }
                },
                error: (erro) => {
                    this.emailMessage = 'Erro ao verificar email';
                }
            });
        }
    }

    protected verificarCpf(cpf: string) {

        const cpfLimpo = cpf.replace(/\D/g, '');

        if (cpfLimpo && cpfLimpo.length === 11) {
            this.loginService.verificarCpfExistente(cpfLimpo).subscribe({
                next: (existe) => {
                    if (existe) {
                        this.cpfMessage = 'CPF já cadastrado no sistema';
                    } else {
                        this.cpfMessage = '';
                    }
                },
                error: (erro) => {
                    this.cpfMessage = 'Erro ao verificar CPF';
                }
            });
        }
    }

    protected gerarCPF(): string {
        const randomDigit = () => Math.floor(Math.random() * 10);

        // gera 9 dígitos aleatórios
        let cpf = Array.from({ length: 9 }, randomDigit);

        // calcula o primeiro dígito verificador
        let soma = cpf.reduce((acc, digit, index) => acc + digit * (10 - index), 0);
        let resto = soma % 11;
        cpf.push(resto < 2 ? 0 : 11 - resto);

        // calcula o segundo dígito verificador
        soma = cpf.reduce((acc, digit, index) => acc + digit * (11 - index), 0);
        resto = soma % 11;
        cpf.push(resto < 2 ? 0 : 11 - resto);

        return cpf.join('');

    }
}
