import { Component, ViewChild } from '@angular/core';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Administrador } from '../../../models/administrador/administrador';
import { LoginService } from '../../../autenticacao/servicos/login.service';
import { NumericoDirective } from '../../../shared/diretivas/numerico/numerico.directive';
import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';
import { CadastroBase } from '../../../shared/base/cadastro-base';

@Component({
  selector: 'app-registrar-admin',
  standalone: true,
  imports: [FormsModule, CommonModule, NumericoDirective, NgxMaskDirective, NgxMaskPipe],
  providers: [provideNgxMask()],
  templateUrl: './registrar-admin.component.html',
  styleUrl: './registrar-admin.component.css'
})
export class RegistrarAdminComponent extends CadastroBase {

  @ViewChild('formCadastro')
  formCadastro!: NgForm;
  message: string = ''; // erros gerais
  administrador: Administrador = {
    cpf: '',
    email: '',
    nome: '',
    telefone: ''
  };

  constructor(
    loginService: LoginService,
    private router: Router
  ) {
    super(loginService);
  }

  gerarCPFValido() {
    this.administrador.cpf = this.gerarCPF();
    // verifica automaticamente o cpf no BD após usário gerá-lo
    setTimeout(() => this.executarVerificacaoCpf(), 100);
  }

  executarVerificacaoEmail() {
    this.verificarEmail(this.administrador.email, this.formCadastro, 'email');
  }

  executarVerificacaoCpf() {
    this.verificarCpf(this.administrador.cpf);
  }

  cadastrar(): void {
    if (this.formCadastro.form.valid) {
      this.loginService.cadastrarAdmin(this.administrador).subscribe({
        next: () => {
          this.router.navigate(["/login"]);
        },
        error: (erro) => {
          console.error('Erro no cadastro:', erro);
        }
      })
    }
  }
}
