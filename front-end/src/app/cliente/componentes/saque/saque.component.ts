import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClienteService } from '../../servico/cliente.service';
import { LoginService } from '../../../autenticacao/servicos/login.service';
import { RouterModule, Router } from '@angular/router';
import { Usuario } from '../../../models/usuario/usuario';
import { FormsModule, NgForm } from '@angular/forms';
import { Saque } from '../../../models/saque/saque.model';
import { ErrorHandlerService } from '../../../shared/servico-erros/error-handler.service';
import { HttpErrorResponse } from '@angular/common/http';
import { SaldoResponse } from '../../../models/saldo-response/saldo-response';


@Component({
  selector: 'app-saque',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './saque.component.html',
  styleUrl: './saque.component.css'
})
export class SaqueComponent implements OnInit {

  @ViewChild('formSaque')
  formSaque!: NgForm;
  saldo!: number;
  limite!: number;
  valorSaque!: number;
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

  retirar(): void {

    if ((this.formSaque.form.valid) && (this.usuario != null)) {
      const saque = new Saque(this.usuario.id, Number(this.valorSaque));
      this.clienteService.saque(saque).subscribe({
        next: () => {
          this.router.navigate(["/cliente/home/" + this.usuario?.id]);
        },
        error: (erro) => {
          console.error('Erro no saque:', erro);
        }
      });
    }
  }
}
