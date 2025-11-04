import { Component, ViewChild, OnInit } from '@angular/core';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Cliente } from '../../../models/cliente/cliente';
import { LoginService } from '../../servicos/login.service';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [FormsModule, CommonModule],
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
    private loginService: LoginService
  ) { }

  consultarCEP() {
    const cep = this.cliente.endereco.cep.replace(/\D/g, ''); // Remove não dígitos

    // Validação do CEP
    if (cep.length !== 8) {
      this.message = 'CEP deve ter 8 dígitos';
      return;
    }

    // Consulta a API ViaCEP
    fetch(`https://viacep.com.br/ws/${cep}/json/`)
      .then(response => response.json())
      .then(data => {
        if (data.erro) {
          this.message = 'CEP não encontrado';
          return;
        }

        // Preenche automaticamente os campos do endereço
        this.cliente.endereco.uf = data.uf;
        this.cliente.endereco.cidade = data.localidade;
        this.cliente.endereco.bairro = data.bairro;
        this.cliente.endereco.rua = data.logradouro;
        this.cliente.endereco.complemento = data.complemento;

        this.message = ''; // Limpa mensagem de erro se houver
      })
      .catch(error => {
        this.message = 'Erro ao consultar CEP';
        console.error('Erro:', error);
      });
  }

}
