import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Deposito } from '../../models/deposito/deposito.model';
import { Saque } from '../../models/saque/saque.model';
import { Transferencia } from '../../models/transferencia/transferencia';
import { SaldoResponse } from '../../models/saldo-response/saldo-response';


@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(
    private httpClient: HttpClient
  ) { }

  //BASE_URL = "http://localhost:8080";
  BASE_URL = environment.url;

  getHttpOptions() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      withCredentials: true
    };
  }

  // R03 -> VAMOS SEGUIR ESTE PADRAO
  buscaSaldo(userId: number): Observable<SaldoResponse> {
    return this.httpClient.get<SaldoResponse>(`${this.BASE_URL}/cliente/saldo/${userId}`,
      this.getHttpOptions());
  }

  // R05
  deposito(deposito: Deposito): Observable<HttpResponse<void>> {
    return this.httpClient.post<void>(`${this.BASE_URL}/cliente/deposito`, deposito, {
      ...this.getHttpOptions(),
      observe: 'response'
    });
  }

  // R06
  saque(saque: Saque): Observable<HttpResponse<void>> {
    return this.httpClient.post<void>(`${this.BASE_URL}/cliente/saque`, saque, {
      ...this.getHttpOptions(),
      observe: 'response'
    });
  }

  // R07
  transferencia(transferencia: Transferencia): Observable<HttpResponse<void>> {
    return this.httpClient.post<void>(`${this.BASE_URL}/cliente/transferencia`, transferencia, {
      ...this.getHttpOptions(),
      observe: 'response'
    }
    );
  }
}
