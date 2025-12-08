import { Directive, OnInit, ViewChild } from "@angular/core";
import { ITransacao } from "./i-transacao";
import { Usuario } from "../../models/usuario/usuario";
import { ClienteService } from "../../cliente/servico/cliente.service";
import { LoginService } from "../../autenticacao/servicos/login.service";
import { ErrorHandlerService } from "../servico-erros/error-handler.service";
import { Router } from "@angular/router";
import { HttpErrorResponse } from "@angular/common/http";
import { SaldoResponse } from "../../models/saldo-response/saldo-response";
import { NgForm } from "@angular/forms";
import { Observable } from "rxjs";
import { Transacao } from "./transacao";

@Directive()
export abstract class TransacaoBase implements ITransacao, OnInit {

    @ViewChild('formTransacao')
    formTransacao!: NgForm;


    saldo!: number;
    limite!: number;
    valorTransacao!: number;
    usuario: Usuario | null = null;
    erroMensagem: string = '';
    dadosConfirmacao: any;
    mostrarConfirmacao: boolean = false;

    protected abstract get entidade(): Transacao;

    constructor(
        protected clienteService: ClienteService,
        protected loginService: LoginService,
        protected errorHandler: ErrorHandlerService,
        protected router: Router
    ) { }

    ngOnInit(): void {
        this.usuario = this.loginService.usuarioLogado;
        this.buscaSaldo();
    }

    buscaSaldo(): void {
        if (!this.usuario?.id) {
            console.error('Usuário não logado ou sem ID');
            return;
        }
        this.clienteService.buscaSaldo(this.usuario.id).subscribe({
            next: (resposta: SaldoResponse) => {
                this.saldo = resposta.saldo;
                this.limite = resposta.limite;
            },
            error: (error: HttpErrorResponse) => {
                this.erroMensagem = this.errorHandler.handleHttpError(error);
            }
        });
    }

    mostrarTelaConfirmacaoTransacao(): void {
        this.mostrarConfirmacao = true;
        this.dadosConfirmacao = this.obterDadosConfirmacao();
    }

    obterDadosConfirmacao(): any {

        const dadosBasicos = {
            // cpf: this.entidade.cpf,
            // email: this.entidade.email,
            // nome: this.entidade.nome,
            // telefone: this.entidade.telefone
            id: this.usuario?.id,
            valor: Number(this.valorTransacao)
        };
        // // se for Cliente (tem salario e endereco), inclui dados extras
        // if ('salario' in this.entidade) {
        //     return {
        //         ...dadosBasicos,
        //         salario: (this.entidade as any).salario,
        //         endereco: (this.entidade as any).endereco,
        //         tipo: 'cliente'
        //     };
        if ('contaDestino' in this.entidade) {
            return {
                ...dadosBasicos,
                contaDestino: (this.entidade as any).contaDestino,
                tipo: 'transferencia'
            };
        }
        // para admin e gerente so dados basicos
        // return {
        //     ...dadosBasicos,
        //     tipo: this.entidade.hasOwnProperty('nivelAcesso') ? 'administrador' : 'gerente'
        // };

        // para deposito e saque so dados basicos
        return {
            ...dadosBasicos,
            tipo: this.entidade.hasOwnProperty('nivelAcesso') ? 'saque' : 'deposito'
        };
    }

    validarTransacao(): boolean {
        return this.formTransacao.form.valid && this.usuario != null;
    }

    // template method
    abstract executarTransacao(): void

    redirecionar(): void {
        this.router.navigate(["/cliente/home/" + this.usuario?.id]);
    };
    //************************************** MÉTODOS PROPRIOS *************************************** */



    protected executarTransacaoServico<T>(operacao: Observable<T>, nomeOperacao: string): void {
        operacao.subscribe({
            next: () => {
                this.redirecionar()
            },
            error: (erro: HttpErrorResponse) => {
                console.error(`Erro no ${nomeOperacao}:`, erro);
                this.erroMensagem = this.errorHandler.handleHttpError(erro);
            }
        });
    }



}
