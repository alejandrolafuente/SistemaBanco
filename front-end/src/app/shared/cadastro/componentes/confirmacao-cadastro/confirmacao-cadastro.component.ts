import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-confirmacao-cadastro',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './confirmacao-cadastro.component.html',
  styleUrl: './confirmacao-cadastro.component.css'
})
export class ConfirmacaoCadastroComponent {

  @Input() dados: any;
  @Output() onConfirmar = new EventEmitter<void>();
  @Output() onEditar = new EventEmitter<void>();
  @Output() onCancelar = new EventEmitter<void>();

  getTipoEntidade(): string {
    
    switch (this.dados?.tipo) {
      case 'cliente': return 'Cliente';
      case 'administrador': return 'Administrador';
      case 'gerente': return 'Gerente';
      default: return 'Usuário';
    }
  }
}
