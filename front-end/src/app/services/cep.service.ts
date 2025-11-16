import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface EnderecoViaCEP {
  cep: string;
  logradouro: string;
  complemento: string;
  bairro: string;
  localidade: string;
  uf: string;
}

@Injectable({
  providedIn: 'root'
})
export class CepService {

  constructor(private http: HttpClient) { }

  consultarCEP(cep: string): Observable<EnderecoViaCEP> {
    const cepLimpo = cep.replace(/\D/g, '');
    return this.http.get<EnderecoViaCEP>(`https://viacep.com.br/ws/${cepLimpo}/json/`);
  }
}
