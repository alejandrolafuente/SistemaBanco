import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { LoginService } from '../../autenticacao/servicos/login.service';
import { environment } from '../../../environments/environment';
import { Deposito } from '../../models/deposito/deposito.model';
import { Saque } from '../../models/saque/saque.model';
import { Transferencia } from '../../models/transferencia/transferencia';


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
  deposito(deposito: Deposito): Observable<HttpResponse<void>> {
    return this.httpClient.post<void>(`${this.BASE_URL}/cliente/deposito`, deposito, {
      ...this.getHttpOptionsWithToken(),
      observe: 'response'
    });
  }

  // R06
  saque(saque: Saque): Observable<HttpResponse<void>> {
    return this.httpClient.post<void>(`${this.BASE_URL}/cliente/saque`, saque, {
      ...this.getHttpOptionsWithToken(),
      observe: 'response'
    });
  }

  // R07
  transferencia(transferencia: Transferencia): Observable<HttpResponse<void>> {
    return this.httpClient.post<void>(`${this.BASE_URL}/cliente/transferencia`, transferencia, {
      ...this.getHttpOptionsWithToken(),
      observe: 'response'
    }
    );
  }
}
