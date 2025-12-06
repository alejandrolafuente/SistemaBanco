import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { TransacaoBase } from '../../../shared/transacao/transacao-base';
import { MonetarioDirective } from '../../../shared/diretivas/monetario/monetario.directive';
import { ConfirmacaoTransacaoComponent } from '../../../shared/transacao/componentes/confirmacao-transacao/confirmacao-transacao.component';
import { Transacao } from '../../../shared/transacao/transacao';
import { Deposito } from '../../../models/deposito/deposito';

@Component({
  selector: 'app-deposito',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, MonetarioDirective, ConfirmacaoTransacaoComponent],
  templateUrl: './deposito.component.html',
  styleUrl: './deposito.component.css'
})
export class DepositoComponent extends TransacaoBase {

  dadosConfirmacao: any;
  mostrarConfirmacao: boolean = false;

  deposito: Deposito = {
    id: 0,
    valor: 0
  };

  protected override get entidade(): Transacao {
    return this.deposito;
  }

  depositar(): void {
    //const deposito = new Deposito(this.usuario!.id, Number(this.valorTransacao));
    // this.executarTransacaoServico(this.clienteService.deposito(deposito), 'depósito');
  }

  override executarTransacao(): void {
    //this.depositar();
    this.mostrarTelaConfirmacaoTransacao();
  }

  override mostrarTelaConfirmacaoTransacao(): void {
    this.mostrarConfirmacao = true;
    this.dadosConfirmacao = this.obterDadosConfirmacao();
  }

  confirmarEnvio(): void {
  }

  //*
  voltarParaEdicao(): void {
    this.mostrarConfirmacao = false;
  }


}
