import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginService } from '../../autenticacao/servicos/login.service';
import { Solicitacao } from '../../models/solicitacao/solicitacao.model';

@Injectable({
  providedIn: 'root'
})
export class GerenteService {

  constructor(
    private httpClient: HttpClient,
    private loginService: LoginService
  ) { }

  BASE_URL = "http://localhost:8080";

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
    return this.httpClient.get<Solicitacao[]>(
      `${this.BASE_URL}/gerentes/${id}/solicitacoes-pendentes`,
      this.getHttpOptionsWithToken()
    );
  }
}
