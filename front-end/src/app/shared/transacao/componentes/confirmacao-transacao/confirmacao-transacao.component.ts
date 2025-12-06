import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-confirmacao-transacao',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './confirmacao-transacao.component.html',
  styleUrl: './confirmacao-transacao.component.css'
})
export class ConfirmacaoTransacaoComponent {

  // @Input() - COMUNICACAO PAI -> FILHO
  // decorador que permite que um componente pai passe dados para um componente filho
  @Input()
  dados: any;

  // @Output() + EventEmitter - COMUNICACAOS FILHO -> PAI
  // permite que um componente filho emita eventos para o componente pai
  @Output()
  onConfirmar = new EventEmitter<void>();

  @Output()
  onEditar = new EventEmitter<void>();

  @Output()
  onCancelar = new EventEmitter<void>();

  getTipoTransacao(): string {

    switch (this.dados?.tipo) {
      case 'deposito':
        return 'deposito';
      case 'saque':
        return 'saque';
      case 'transferencia':
        return 'transferencia';
      default:
        return 'Transacao';
    }
  }
}
