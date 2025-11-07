import { Component, ViewChild, OnInit } from '@angular/core';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Cliente } from '../../../models/cliente/cliente';
import { LoginService } from '../../servicos/login.service';
import { NumericoDirective } from '../../../shared/diretivas/numerico/numerico.directive';
import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';
import { CadastroBase } from '../../../shared/base/cadastro-base';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [FormsModule, CommonModule, NumericoDirective, NgxMaskDirective, NgxMaskPipe],
  providers: [provideNgxMask()],
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.css'
})
export class CadastroComponent extends CadastroBase {

  @ViewChild('formCadastro')
  formCadastro!: NgForm;
  message: string = ''; // erros gerais, como cep
  cepMessage: string = '';
  cliente: Cliente = {
    cpf: '',
    email: '',
    nome: '',
    telefone: '',
    salario: 0,
    endereco: {
      cep: '',
      uf: '',
      cidade: '',
      bairro: '',
      rua: '',
      numero: '',
      complemento: ''
    }
  };

  constructor(
    loginService: LoginService,
    private router: Router
  ) {
    super(loginService);
  }

  gerarCPFValido() {
    this.cliente.cpf = this.gerarCPF();
    // verifica automaticamente o cpf no BD após gerar
    setTimeout(() => this.executarVerificacaoCpf(), 100);
  }

  executarVerificacaoEmail() {
    this.verificarEmail(this.cliente.email, this.formCadastro, 'email');
  }

  executarVerificacaoCpf() {
    this.verificarCpf(this.cliente.cpf);
  }

  consultarCEP() {
    const cep = this.cliente.endereco.cep.replace(/\D/g, ''); // remove não dígitos

    // consulta a API ViaCEP
    if (cep.length === 8) {


      fetch(`https://viacep.com.br/ws/${cep}/json/`)
        .then(response => response.json())
        .then(data => {
          if (data.erro) {
            this.cepMessage = 'CEP não encontrado';
            return;
          }

          // preenche automaticamente os campos do endereço
          this.cliente.endereco.uf = data.uf;
          this.cliente.endereco.cidade = data.localidade;
          this.cliente.endereco.bairro = data.bairro;
          this.cliente.endereco.rua = data.logradouro;
          this.cliente.endereco.complemento = data.complemento;

          this.message = ''; // limpa mensagem de erro se houver
        })
        .catch(error => {
          this.cepMessage = 'Erro ao consultar CEP';
          console.error('Erro:', error);
        });
    }
  }

  cadastrar(): void {
    if (this.formCadastro.form.valid) {
      this.loginService.cadastrar(this.cliente).subscribe({
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
