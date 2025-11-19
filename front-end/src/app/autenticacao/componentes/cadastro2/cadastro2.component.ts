import { Component, ViewChild } from '@angular/core';
import { CadastroBase } from '../../../shared/cadastro/cadastro-base';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Cliente } from '../../../models/cliente/cliente';
import { LoginService } from '../../servicos/login.service';
import { CepService, EnderecoViaCEP } from '../../../services/cep.service';
import { NumericoDirective } from '../../../shared/diretivas/numerico/numerico.directive';
import { MonetarioDirective } from '../../../shared/diretivas/monetario/monetario.directive';
import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';
import { IEntidadeCadastravel } from '../../../shared/cadastro/ientidade-cadastravel';
import { ConfirmacaoCadastroComponent } from '../../../shared/cadastro/componentes/confirmacao-cadastro/confirmacao-cadastro.component';

@Component({
  selector: 'app-cadastro2',
  standalone: true,
  imports: [FormsModule, CommonModule, NumericoDirective, MonetarioDirective,
    NgxMaskDirective, NgxMaskPipe, ConfirmacaoCadastroComponent],
  providers: [provideNgxMask()],
  templateUrl: './cadastro2.component.html',
  styleUrl: './cadastro2.component.css'
})
export class Cadastro2Component extends CadastroBase {

  @ViewChild('formCadastro') formCadastro!: NgForm;
  cepMessage: string = '';

  // campos para controle da tela de confirmacao
  mostrarConfirmacao: boolean = false;
  dadosConfirmacao: any;

  cliente: Cliente = {
    cpf: '', //2
    email: '',
    nome: '', //1
    telefone: '',
    salario: null,
    endereco: {
      cep: '',
      uf: '',
      cidade: '',
      bairro: '',
      rua: '',
      numero: '',
      complemento: ''
    }
  };

  constructor(
    loginService: LoginService,
    private router: Router,
    private cepService: CepService
  ) {
    super(loginService);
  }


  protected override get form(): NgForm {
    return this.formCadastro;
  }
  protected override get entidade(): IEntidadeCadastravel {
    return this.cliente;
  }

  gerarCPFValido(): void {
    this.cliente.cpf = this.gerarCPF();
  }

  executarVerificacaoCpf(): void {
    this.verificarCpf(this.cliente.cpf);
  }

  executarVerificacaoEmail(): void {
    this.verificarEmail(this.cliente.email);
  }

  consultarCEP(): void {
    const cep = this.cliente.endereco.cep.replace(/\D/g, '');

    if (cep.length === 8) {
      this.cepService.consultarCEP(cep).subscribe({
        next: (data: EnderecoViaCEP) => {
          if ('erro' in data) {
            this.cepMessage = 'CEP não encontrado';
            return;
          }

          this.preencherEndereco(data);
          this.cepMessage = '';
        },
        error: (error) => {
          this.cepMessage = 'Erro ao consultar CEP';
          console.error('Erro na consulta de CEP:', error);
        }
      });
    }
  }


  private preencherEndereco(endereco: EnderecoViaCEP): void {
    this.cliente.endereco.uf = endereco.uf;
    this.cliente.endereco.cidade = endereco.localidade;
    this.cliente.endereco.bairro = endereco.bairro;
    this.cliente.endereco.rua = endereco.logradouro;
    this.cliente.endereco.complemento = endereco.complemento;
  }

  // metodos para controle da confirmacao
  protected override processarCadastro(): void {
    // mostra tela de confirmacao ao inves de enviar diretamente
    this.mostrarConfirmacao = true;
    this.dadosConfirmacao = this.obterDadosConfirmacao();
  }

  confirmarEnvio(): void {
    this.loginService.cadastrar(this.cliente).subscribe({
      next: () => {
        this.router.navigate(["/login"]);
      },
      error: (erro) => {
        console.error('Erro no cadastro do cliente:', erro);
        this.mostrarConfirmacao = false;
      }
    });
  }

  voltarParaEdicao(): void {
    this.mostrarConfirmacao = false;
  }

}
