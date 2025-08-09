import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Login } from '../../models/login/login.model';
import { Usuario } from '../../models/usuario/usuario.model';

const LS_LOGIN_KEY: string = "usuarioLogado";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  BASE_URL = "http://localhost:8080";

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private httpClient: HttpClient) { }

  public get usuarioLogado(): Usuario | null {
    let usuario = localStorage[LS_LOGIN_KEY];
    return (usuario ? JSON.parse(localStorage[LS_LOGIN_KEY]) : null);
  }

  public set usuarioLogado(usuario: Usuario) {
    localStorage[LS_LOGIN_KEY] = JSON.stringify(usuario);
  }

  login(login: Login): Observable<Usuario> {
    let usuarioLogado = this.httpClient.post<Usuario>(this.BASE_URL + '/autenticacao/login', JSON.stringify(login), this.httpOptions);
    return usuarioLogado;
  }
  // colocar metodo de logout
}
