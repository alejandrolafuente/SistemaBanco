import { Component, ViewChild } from '@angular/core';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { LoginService } from '../../../autenticacao/servicos/login.service';
import { Gerente } from '../../../models/gerente/gerente';
import { NumericoDirective } from '../../../shared/diretivas/numerico/numerico.directive';
import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';
import { CadastroBase } from '../../../shared/cadastro/cadastro-base';
import { IEntidadeCadastravel } from '../../../shared/cadastro/ientidade-cadastravel';
import { ConfirmacaoCadastroComponent } from '../../../shared/cadastro/componentes/confirmacao-cadastro/confirmacao-cadastro.component';


@Component({
  selector: 'app-insercao-gerente',
  standalone: true,
  imports: [FormsModule, CommonModule, NumericoDirective,
    NgxMaskDirective, NgxMaskPipe, ConfirmacaoCadastroComponent],
  providers: [provideNgxMask()],
  templateUrl: './insercao-gerente.component.html',
  styleUrl: './insercao-gerente.component.css'
})
export class InsercaoGerenteComponent extends CadastroBase {

  message: string = '';
  //dadosConfirmacao: any;

  gerente: Gerente = {
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
    return this.formCadastro
  }
  protected override get entidade(): IEntidadeCadastravel {
    return this.gerente
  }
  //*
  executarVerificacaoCpf(): void {
    this.verificarCpf(this.gerente.cpf);
  }

  //*
  executarVerificacaoEmail(): void {
    this.verificarEmail(this.gerente.email);
  }
  
  //*
  confirmarEnvio(): void {
    console.log('USUÁRIO LOGADO:', this.loginService.usuarioLogado);
    console.log('PERFIL:', this.loginService.usuarioLogado?.perfil);

    this.loginService.cadastrarGerente(this.gerente).subscribe({
      next: () => {
        this.router.navigate(["/login"]);
      },
      error: (erro) => {
        console.error('Erro no cadastro do gerente:', erro);
        this.mostrarConfirmacao = false;
      }
    });
  }

  //*
  voltarParaEdicao(): void {
    this.mostrarConfirmacao = false;
  }

}
