import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GerenteDTO } from '../../../models/gerenteDTO/gerente-dto';

@Component({
  selector: 'app-listagem-gerentes',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './listagem-gerentes.component.html',
  styleUrl: './listagem-gerentes.component.css'
})
export class ListagemGerentesComponent implements OnInit {

  gerentes: GerenteDTO[] = [];

  ngOnInit(): void {
    this.listarGerentes();
  }

  listarGerentes() {
    this.gerentes = [
      {
        id: 1,
        nome: 'Carlos Silva',
        cpf: '123.456.789-00',
        email: 'carlos.silva@banco.com',
        telefone: '(11) 9999-8888',
        numeroClientes: 15,
        saldosPositivos: 9,
        saldosNegativos: 6
      },
      {
        id: 2,
        nome: 'Ana Santos',
        cpf: '987.654.321-00',
        email: 'ana.santos@banco.com',
        telefone: '(11) 7777-6666',
        numeroClientes: 22,
        saldosPositivos: 14,
        saldosNegativos: 8
      },
      {
        id: 3,
        nome: 'João Oliveira',
        cpf: '456.789.123-00',
        email: 'joao.oliveira@banco.com',
        telefone: '(11) 5555-4444',
        numeroClientes: 18,
        saldosPositivos: 11,
        saldosNegativos: 7
      },
      {
        id: 4,
        nome: 'Maria Fernandes',
        cpf: '789.123.456-00',
        email: 'maria.fernandes@banco.com',
        telefone: '(11) 3333-2222',
        numeroClientes: 30,
        saldosPositivos: 19,
        saldosNegativos: 11
      },
      {
        id: 5,
        nome: 'Pedro Costa',
        cpf: '321.654.987-00',
        email: 'pedro.costa@banco.com',
        telefone: '(11) 1111-0000',
        numeroClientes: 12,
        saldosPositivos: 7,
        saldosNegativos: 5
      }
    ];
  }
}
