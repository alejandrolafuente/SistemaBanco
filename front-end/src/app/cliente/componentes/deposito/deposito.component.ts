import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClienteService } from '../../servico/cliente.service';
import { LoginService } from '../../../autenticacao/servicos/login.service';
import { RouterModule } from '@angular/router';
import { Usuario } from '../../../models/usuario/usuario.model';
import { NgForm, FormsModule } from '@angular/forms';

@Component({
  selector: 'app-deposito',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './deposito.component.html',
  styleUrl: './deposito.component.css'
})
export class DepositoComponent implements OnInit {

  saldo!: number;
  valorDeposito!: number;
  usuario: Usuario | null = null;

  constructor(
    private clienteService: ClienteService,
    private loginService: LoginService
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
      next: (response) => {
        this.saldo = response;
        console.log("saldo: " + response)
      },
      error: (err) => {
        console.error('Erro ao buscar o saldo', err);
      }
    })
  }

  doLogin(): void { }

}
