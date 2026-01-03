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
import { ErrorHandlerService } from '../../../shared/servico-erros/error-handler.service';
import { GerenteResponse } from '../../../models/gerente-response/gerente-response';
import { HttpErrorResponse } from '@angular/common/http';


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

  gerente: Gerente = {
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
    return this.gerente
  }

  confirmarEnvio(): void {

    this.loginService.cadastrarGerente(this.gerente).subscribe({
      next: (resposta: GerenteResponse) => {
        alert(`Gerente ${resposta.nome} adicionado com sucesso!\nA senha foi enviada para o email: 
          ${resposta.email}\n\nVocê será redirecionado para a tela de gerentes`);
        this.router.navigate(["/admin/listar-gerentes"]);
      },
      error: (error: HttpErrorResponse) => {
        this.erroMensagem = this.errorHandler.handleHttpError(error);
        this.mostrarConfirmacao = false;
      }
    });
  }

}
