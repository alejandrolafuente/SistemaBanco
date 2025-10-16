import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginService } from '../../autenticacao/servicos/login.service';
import { Solicitacao } from '../../models/solicitacao/solicitacao.model';
import { ClienteAprovado } from '../../models/cliente-aprovado/cliente-aprovado.model';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GerenteService {

  constructor(
    private httpClient: HttpClient,
    private loginService: LoginService
  ) { }

  //BASE_URL = "http://localhost:8080";
  BASE_URL = environment.url;

  getHttpOptionsWithToken() {
    const token = this.loginService.usuarioLogado?.token;
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      }),
      withCredentials: true
    };
  }

  solicitacoesPendentes(id: number): Observable<Solicitacao[]> {
    return this.httpClient.get<Solicitacao[]>(`${this.BASE_URL}/gerentes/${id}/solicitacoes-pendentes`,
      this.getHttpOptionsWithToken());
  }

  aprovarCliente(idConta: number): Observable<ClienteAprovado> {
    return this.httpClient.put<ClienteAprovado>(`${this.BASE_URL}/gerentes/aprovar-conta/${idConta}`, null,
      this.getHttpOptionsWithToken());
  }
}
