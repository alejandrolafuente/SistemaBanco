import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { R19ResDTO } from '../../../models/gerenteDTO/R19ResDTO';

@Component({
  selector: 'app-listagem-gerentes',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './listagem-gerentes.component.html',
  styleUrl: './listagem-gerentes.component.css'
})
export class ListagemGerentesComponent implements OnInit {

  gerentes: R19ResDTO[] = [];

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
        telefone: '(11) 9999-8888'
      },
      {
        id: 2,
        nome: 'Ana Santos',
        cpf: '987.654.321-00',
        email: 'ana.santos@banco.com',
        telefone: '(11) 7777-6666'
      },
      {
        id: 3,
        nome: 'João Oliveira',
        cpf: '456.789.123-00',
        email: 'joao.oliveira@banco.com',
        telefone: '(11) 5555-4444'
      },
      {
        id: 4,
        nome: 'Maria Fernandes',
        cpf: '789.123.456-00',
        email: 'maria.fernandes@banco.com',
        telefone: '(11) 3333-2222'
      },
      {
        id: 5,
        nome: 'Pedro Costa',
        cpf: '321.654.987-00',
        email: 'pedro.costa@banco.com',
        telefone: '(11) 1111-0000'
      }
    ];
  }
}
