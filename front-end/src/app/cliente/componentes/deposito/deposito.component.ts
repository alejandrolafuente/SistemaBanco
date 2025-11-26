import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClienteService } from '../../servico/cliente.service';
import { LoginService } from '../../../autenticacao/servicos/login.service';
import { RouterModule, Router } from '@angular/router';
import { Usuario } from '../../../models/usuario/usuario';
import { FormsModule, NgForm } from '@angular/forms';
import { Deposito } from '../../../models/deposito/deposito.model';
import { ErrorHandlerService } from '../../../shared/servico-erros/error-handler.service';
import { HttpErrorResponse } from '@angular/common/http';
import { SaldoResponse } from '../../../models/saldo-response/saldo-response';

@Component({
  selector: 'app-deposito',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './deposito.component.html',
  styleUrl: './deposito.component.css'
})
export class DepositoComponent implements OnInit {

  @ViewChild('formDeposit')
  formDeposit!: NgForm;
  saldo!: number;
  limite!: number;
  valorDeposito!: number;
  usuario: Usuario | null = null;
  erroMensagem: string = '';

  constructor(
    private clienteService: ClienteService,
    private loginService: LoginService,
    private errorHandler: ErrorHandlerService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.usuario = this.loginService.usuarioLogado;
    this.buscaSaldo();
  }

  buscaSaldo() {
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
    })
  }

  depositar(): void {
    if ((this.formDeposit.form.valid) && (this.usuario != null)) {
      const deposito = new Deposito(this.usuario.id, Number(this.valorDeposito));

      this.clienteService.deposito(deposito).subscribe({
        next: () => {
          this.router.navigate(["/cliente/home/" + this.usuario?.id]);
        },
        error: (erro) => {
          console.error('Erro no depósito:', erro);
        }
      });
    }
  }

}
