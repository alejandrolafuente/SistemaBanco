import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Deposito } from '../../../models/deposito/deposito.model';
import { TransacaoBase } from '../../../shared/transacao/transacao-base';

@Component({
  selector: 'app-deposito',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './deposito.component.html',
  styleUrl: './deposito.component.css'
})
export class DepositoComponent extends TransacaoBase {

  depositar(): void {
    const deposito = new Deposito(this.usuario!.id, Number(this.valorTransacao));
    this.executarTransacaoServico(this.clienteService.deposito(deposito), 'depósito');
  }

  override executarTransacao(): void {
    this.depositar();
  }



}
