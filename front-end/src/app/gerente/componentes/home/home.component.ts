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

  // aprovarCliente($event: any, solicitacao: Solicitacao) {
  //   $event.preventDefault();
  //   if (confirm(`Confirmar aprovação do cliente ${solicitacao.nome}?`)) {
  //     this.gerenteService.aprovarCliente(solicitacao.contaId!).subscribe({
  //       next: (response) => {
  //         console.log('Cliente aprovado com sucesso:', response);
  //         setTimeout(() => {
  //           this.solicitacoesPendentes();
  //         }, 2000);
  //       },
  //       error: (err) => {
  //         console.error('Erro ao aprovar cliente', err);
  //       }
  //     });
  //   }
  // }
}
