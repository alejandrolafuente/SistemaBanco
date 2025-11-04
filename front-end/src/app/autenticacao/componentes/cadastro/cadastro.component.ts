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

}
