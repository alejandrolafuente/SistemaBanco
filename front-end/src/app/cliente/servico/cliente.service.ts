import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginService } from '../../autenticacao/servicos/login.service';
import { environment } from '../../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ClienteService {

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

  // R03 
  buscaSaldo(userId: number): Observable<number> {
    return this.httpClient.get<number>(`${this.BASE_URL}/cliente/saldo/${userId}`,
      this.getHttpOptionsWithToken());
  }

  // R05
  deposito()
}
