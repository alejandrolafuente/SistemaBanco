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
import { ErrorHandlerService } from '../../../shared/servico-erros/error-handler.service';
import { AdminResponse } from '../../../models/adminresponse/admin-response';
import { HttpErrorResponse } from '@angular/common/http';

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

  administrador: Administrador = {
    cpf: '',
    email: '',
    nome: '',
    telefone: ''
  }

  constructor(
    loginService: LoginService,
    private router: Router,
    private errorHandler: ErrorHandlerService
  ) {
    super(loginService);
  }

  protected override get entidade(): IEntidadeCadastravel {
    return this.administrador;
  }

  confirmarEnvio(): void {
    this.loginService.cadastrarAdmin(this.administrador).subscribe({
      next: (resposta: AdminResponse) => {
        // mostra mensagem de sucesso
        // implementar tela dedicada depois!
        alert(`Administrador ${resposta.nome} adicionado com sucesso!\nA senha foi enviada para o email: 
          ${resposta.email}\n\nVocê será redirecionado para a tela de login`);
        // redireciona para login APOS o usuário clicar em ok
        this.router.navigate(["/login"]);
        // redireciona para login apos 2 segundos
        // setTimeout(() => {
        //   this.router.navigate(["/login"]);
        // }, 2000);
      },
      error: (error: HttpErrorResponse) => {
        this.erroMensagem = this.errorHandler.handleHttpError(error);
        this.mostrarConfirmacao = false;
      }
    });
  }


}
