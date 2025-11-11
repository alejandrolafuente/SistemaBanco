import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginService } from '../../autenticacao/servicos/login.service';
import { Solicitacao } from '../../models/solicitacao/solicitacao.model';
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

  getHttpOptions() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      withCredentials: true // envia o cookie autimaticamente!
    };
  }

  solicitacoesPendentes(id: number): Observable<Solicitacao[]> {
    return this.httpClient.get<Solicitacao[]>(`${this.BASE_URL}/gerentes/${id}/solicitacoes-pendentes`,
      this.getHttpOptions());
  }

  // aprovarCliente(idConta: number): Observable<ClienteAprovado> {
  //   return this.httpClient.put<ClienteAprovado>(`${this.BASE_URL}/gerentes/aprovar-conta/${idConta}`, null,
  //     this.getHttpOptionsWithToken());
  // }
}
