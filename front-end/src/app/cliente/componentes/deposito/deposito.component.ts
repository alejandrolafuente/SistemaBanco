import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClienteService } from '../../servico/cliente.service';
import { LoginService } from '../../../autenticacao/servicos/login.service';
import { RouterModule, Router } from '@angular/router';
import { Usuario } from '../../../models/usuario/usuario.model';
import { FormsModule, NgForm } from '@angular/forms';
import { Deposito } from '../../../models/deposito/deposito.model';

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
  valorDeposito!: number;
  usuario: Usuario | null = null;

  constructor(
    private clienteService: ClienteService,
    private loginService: LoginService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.usuario = this.loginService.usuarioLogado;
    console.log(this.usuario);
    this.buscaSaldo();
  }

  buscaSaldo() {
    if (!this.usuario?.id) {
      console.error('Usuário não logado ou sem ID');
      return;
    }
    this.clienteService.buscaSaldo(this.usuario.id).subscribe({
      next: (response) => {
        this.saldo = response;
        console.log("saldo: " + response)
      },
      error: (erro) => {
        console.error('Erro ao buscar o saldo', erro);
      }
    })
  }

  depositar(): void {
    if ((this.formDeposit.form.valid) && (this.usuario != null)) {
      const deposito = new Deposito(this.usuario.id, Number(this.valorDeposito));

      this.clienteService.deposito(deposito).subscribe({
        next: () => {
          console.log('Depósito realizado com sucesso');
          this.router.navigate(["/cliente/home/" + this.usuario?.id]);
        },
        error: (erro) => {
          console.error('Erro no depósito:', erro);
        }
      });
    }
  }

}
