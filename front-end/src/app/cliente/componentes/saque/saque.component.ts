import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Saque } from '../../../models/saque/saque.model';
import { TransacaoBase } from '../../../shared/transacao/transacao-base';
import { Transacao } from '../../../shared/transacao/transacao';


@Component({
  selector: 'app-saque',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './saque.component.html',
  styleUrl: './saque.component.css'
})
export class SaqueComponent extends TransacaoBase {

  protected override get entidade(): Transacao {
    throw new Error('Method not implemented.');
  }
  override mostrarTelaConfirmacaoTransacao(): void {
    throw new Error('Method not implemented.');
  }

  retirar(): void {
    const saque = new Saque(this.usuario!.id, Number(this.valorTransacao));
    this.executarTransacaoServico(this.clienteService.saque(saque), 'saque');
  }

  override executarTransacao(): void {
    this.retirar();
  }

}
