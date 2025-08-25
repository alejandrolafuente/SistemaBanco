import { Component, OnInit } from '@angular/core';
import { Solicitacao } from '../../../models/solicitacao/solicitacao.model';
import { GerenteService } from '../../servico/gerente.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  gerenteId!: number;
  solicitacoes: Solicitacao[] = [];

  constructor(
    private gerenteService: GerenteService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.gerenteId = this.route.snapshot.params['id'];
    this.solicitacoesPendentes();
  }

  solicitacoesPendentes(): void {
    this.gerenteService.solicitacoesPendentes(this.gerenteId).subscribe({
      next: (solicitacoes: Solicitacao[]) => {
        this.solicitacoes = solicitacoes;
        console.log('Solicitações carregadas:', solicitacoes);
      },
      error: (error) => {
        console.error('Erro ao carregar solicitações:', error);
      }
    });
  }
}
