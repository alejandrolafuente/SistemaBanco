import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute, RouterLink, RouterModule } from '@angular/router';
import { ClienteService } from '../../servico/cliente.service';
import { LoginService } from '../../../autenticacao/servicos/login.service';
import { Usuario } from '../../../models/usuario/usuario.model';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  nomeCliente: string | undefined;
  saldo!: number;
  usuario: Usuario | null = null;
  userId!: number;

  constructor(
    private clienteService: ClienteService,
    private loginService: LoginService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.params['id'];
    this.usuario = this.loginService.usuarioLogado;
    this.nomeCliente = this.usuario?.nome;
    this.buscaSaldo();
  }

  buscaSaldo() {
    this.clienteService.buscaSaldo(this.userId).subscribe({
      next: (response) => {
        this.saldo = response;
        console.log("saldo: " + response)
      },
      error: (err) => {
        console.error('Erro ao buscar o saldo', err);
      }
    })
  }
}
