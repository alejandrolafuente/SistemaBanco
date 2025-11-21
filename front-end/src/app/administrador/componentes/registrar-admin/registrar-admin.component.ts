import { Component, ViewChild } from '@angular/core';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Administrador } from '../../../models/administrador/administrador';
import { LoginService } from '../../../autenticacao/servicos/login.service';
import { NumericoDirective } from '../../../shared/diretivas/numerico/numerico.directive';
import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';
import { CadastroBase } from '../../../shared/cadastro/cadastro-base';
import { IEntidadeCadastravel } from '../../../shared/cadastro/ientidade-cadastravel';

@Component({
  selector: 'app-registrar-admin',
  standalone: true,
  imports: [FormsModule, CommonModule, NumericoDirective, NgxMaskDirective, NgxMaskPipe],
  providers: [provideNgxMask()],
  templateUrl: './registrar-admin.component.html',
  styleUrl: './registrar-admin.component.css'
})
export class RegistrarAdminComponent extends CadastroBase {

  
}
