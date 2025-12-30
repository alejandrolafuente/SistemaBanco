import { Component, ViewChild } from '@angular/core';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Administrador } from '../../../models/administrador/administrador';
import { LoginService } from '../../../autenticacao/servicos/login.service';
import { NumericoDirective } from '../../../shared/diretivas/numerico/numerico.directive';
import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';
import { CadastroBase } from '../../../shared/cadastro/cadastro-base';
import { IEntidadeCadastravel } from '../../../shared/cadastro/ientidade-cadastravel';
import { ConfirmacaoCadastroComponent } from '../../../shared/cadastro/componentes/confirmacao-cadastro/confirmacao-cadastro.component';

@Component({
  selector: 'app-registrar-admin',
  standalone: true,
  imports: [FormsModule, CommonModule, NumericoDirective,
    NgxMaskDirective, NgxMaskPipe, ConfirmacaoCadastroComponent],
  providers: [provideNgxMask()],
  templateUrl: './registrar-admin.component.html',
  styleUrl: './registrar-admin.component.css'
})
export class RegistrarAdminComponent extends CadastroBase {
  //*
  @ViewChild('formCadastro')
  formCadastro!: NgForm;

  dadosConfirmacao: any;

  administrador: Administrador = {
    cpf: '',
    email: '',
    nome: '',
    telefone: ''
  }

  constructor(
    loginService: LoginService,
    private router: Router
  ) {
    super(loginService);
  }

  protected override get form(): NgForm {
    return this.formCadastro;
  }
  protected override get entidade(): IEntidadeCadastravel {
    return this.administrador;
  }

  //*
  gerarCPFValido(): void {
    this.administrador.cpf = this.gerarCPF();
  }

  //*
  executarVerificacaoCpf(): void {
    this.verificarCpf(this.administrador.cpf);
  }

  //*
  executarVerificacaoEmail(): void {
    this.verificarEmail(this.administrador.email);
  }

  protected override processarCadastro(): void {
    this.mostrarConfirmacao = true;
    this.dadosConfirmacao = this.obterDadosConfirmacao();
  }
  //*
  confirmarEnvio(): void {
    this.loginService.cadastrarAdmin(this.administrador).subscribe({
      next: () => {
        this.router.navigate(["/login"]);
      },
      error: (erro) => {
        console.error('Erro no cadastro do admin:', erro);
        this.mostrarConfirmacao = false;
      }
    });
  }

  //*
  voltarParaEdicao(): void {
    this.mostrarConfirmacao = false;
  }


}
