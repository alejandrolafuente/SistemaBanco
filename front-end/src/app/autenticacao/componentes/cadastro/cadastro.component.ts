import { Component, ViewChild, OnInit } from '@angular/core';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Cliente } from '../../../models/cliente/cliente';
import { LoginService } from '../../servicos/login.service';
import { NumericoDirective } from '../../../shared/diretivas/numerico/numerico.directive';
import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [FormsModule, CommonModule, NumericoDirective, NgxMaskDirective, NgxMaskPipe],
  providers: [provideNgxMask()],
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.css'
})
export class CadastroComponent {

  @ViewChild('formCadastro')
  formCadastro!: NgForm;
  message!: string;
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
    private loginService: LoginService,
    private router: Router
  ) { }

  gerarCPFValido() {
    this.cliente.cpf = this.gerarCPF();
  }

  private gerarCPF(): string {
    const randomDigit = () => Math.floor(Math.random() * 10);

    // gera 9 dígitos aleatórios
    let cpf = Array.from({ length: 9 }, randomDigit);

    // calcula o primeiro dígito verificador
    let soma = cpf.reduce((acc, digit, index) => acc + digit * (10 - index), 0);
    let resto = soma % 11;
    cpf.push(resto < 2 ? 0 : 11 - resto);

    // calcula o segundo dígito verificador
    soma = cpf.reduce((acc, digit, index) => acc + digit * (11 - index), 0);
    resto = soma % 11;
    cpf.push(resto < 2 ? 0 : 11 - resto);

    return cpf.join('');

  }

  consultarCEP() {
    const cep = this.cliente.endereco.cep.replace(/\D/g, ''); // remove não dígitos

    // validação do CEP
    if (cep.length !== 8) {
      this.message = 'CEP deve ter 8 dígitos';
      return;
    }

    // consulta a API ViaCEP
    fetch(`https://viacep.com.br/ws/${cep}/json/`)
      .then(response => response.json())
      .then(data => {
        if (data.erro) {
          this.message = 'CEP não encontrado';
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
        this.message = 'Erro ao consultar CEP';
        console.error('Erro:', error);
      });
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
