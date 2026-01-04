import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Login } from '../../models/login/login';
import { Usuario } from '../../models/usuario/usuario';
import { environment } from '../../../environments/environment';
import { Cliente } from '../../models/cliente/cliente';
import { Administrador } from '../../models/administrador/administrador';
import { Gerente } from '../../models/gerente/gerente';
import { AdminResponse } from '../../models/adminresponse/admin-response';
import { GerenteResponse } from '../../models/gerente-response/gerente-response';
import { ClienteResponse } from '../../models/cliente-response/cliente-response';

const LS_LOGIN_KEY: string = "usuarioLogado";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

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
    return this.httpClient.get<{ existe: boolean }>(`${this.BASE_URL}/api/usuarios/verificar-cpf/${cpf}`
    ).pipe(
      map(response => response.existe)
    );
  }
  // R01 - cadastrar cliente
  cadastrarCliente(cliente: Cliente): Observable<ClienteResponse> {
    return this.httpClient.post<ClienteResponse>(`${this.BASE_URL}/cliente/register`, cliente);
  }

  // R02
  login(login: Login): Observable<Usuario> {
    return this.httpClient.post<Usuario>(this.BASE_URL +
      '/auth/login', login, this.getHttpOptions());

  }

  // R17 - cadastrar gerente
  cadastrarGerente(gerente: Gerente): Observable<GerenteResponse> {
    return this.httpClient.post<GerenteResponse>(`${this.BASE_URL}/admin/novo-gerente`, gerente, {
      ...this.getHttpOptions()
    });
  }

  // R21 - cadastrar admin - requisito extra
  cadastrarAdmin(administrador: Administrador): Observable<AdminResponse> {
    return this.httpClient.post<AdminResponse>(`${this.BASE_URL}/admin`,
      administrador);
  }

  logout() {
    delete localStorage[LS_LOGIN_KEY];
  }

}
