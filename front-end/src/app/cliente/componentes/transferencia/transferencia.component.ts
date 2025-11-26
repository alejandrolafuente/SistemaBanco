import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClienteService } from '../../servico/cliente.service';
import { LoginService } from '../../../autenticacao/servicos/login.service';
import { RouterModule, Router } from '@angular/router';
import { Usuario } from '../../../models/usuario/usuario';
import { FormsModule, NgForm } from '@angular/forms';
import { Transferencia } from '../../../models/transferencia/transferencia';
import { ErrorHandlerService } from '../../../shared/servico-erros/error-handler.service';
import { HttpErrorResponse } from '@angular/common/http';
import { SaldoResponse } from '../../../models/saldo-response/saldo-response';

@Component({
  selector: 'app-transferencia',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './transferencia.component.html',
  styleUrl: './transferencia.component.css'
})
export class TransferenciaComponent implements OnInit {

  @ViewChild('formTransferencia')
  formTransferencia!: NgForm;
  saldo!: number;
  limite!: number;
  valorTransferencia!: number;
  contaDestino!: string;
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

  transferir(): void {
    if ((this.formTransferencia.form.valid) && (this.usuario != null)) {

      const transferencia: Transferencia = {
        valor: Number(this.valorTransferencia),
        contaDestino: this.contaDestino
      };

      this.clienteService.transferencia(transferencia).subscribe({
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
