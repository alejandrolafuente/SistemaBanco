import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Login } from '../../models/login/login.model';
import { Usuario } from '../../models/usuario/usuario.model';
import { environment } from '../../../environments/environment';
import { Cliente } from '../../models/cliente/cliente';

const LS_LOGIN_KEY: string = "usuarioLogado";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  //BASE_URL = "http://localhost:8080";
  BASE_URL = environment.url;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    withCredentials: true
  };

  constructor(private httpClient: HttpClient) { }

  public get usuarioLogado(): Usuario | null {
    let usuario = localStorage[LS_LOGIN_KEY];
    return (usuario ? JSON.parse(localStorage[LS_LOGIN_KEY]) : null);
  }

  public set usuarioLogado(usuario: Usuario) {
    localStorage[LS_LOGIN_KEY] = JSON.stringify(usuario);
  }


  verificarEmailExistente(email: string): Observable<boolean> {
    return this.httpClient.get<{ existe: boolean }>(`${this.BASE_URL}/api/usuarios/verificar-email/${email}`
    ).pipe(
      map(response => response.existe)
    );
  }

  verificarCpfExistente(cpf: string): Observable<boolean> {
    return this.httpClient.get<{ existe: boolean }>(
      `${this.BASE_URL}/api/usuarios/verificar-cpf/${cpf}`
    ).pipe(
      map(response => response.existe)
    );
  }
  // R01
  cadastrar(cliente: Cliente): Observable<HttpResponse<void>> {
    return this.httpClient.post<void>(`${this.BASE_URL}/cliente/register`, cliente, {
      observe: 'response'
    });
  }

  // R02
  login(login: Login): Observable<Usuario> {
    let usuarioLogado = this.httpClient.post<Usuario>(this.BASE_URL +
      '/auth/login', JSON.stringify(login), this.httpOptions);
    return usuarioLogado;
  }

  logout() {
    delete localStorage[LS_LOGIN_KEY];
  }

}
