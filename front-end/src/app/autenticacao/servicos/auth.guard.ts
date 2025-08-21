import { CanActivateFn } from '@angular/router';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';
import { Router } from '@angular/router';

// export const authGuard: CanActivateFn = (route, state) => {
//   return true;
// };
@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private loginService: LoginService,
    private router: Router
  ) { }


  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {


    const loggedInUser = this.loginService.usuarioLogado; // getter

    let url = state.url;

    if (loggedInUser) { // if  user está logado no local storage...

      if (route.data?.['role'] && route.data?.['role'].indexOf(loggedInUser.perfil) === -1) {
        // Se o perfil do usuário não está na rota
        // vai para login
        this.router.navigate(['/login'],
          { queryParams: { error: "Proibido o acesso a " + url } });
        return false;
      }
      // em qualquer outro caso, permite o acesso
      return true;
    }
    // se nao está logado vai para o login
    this.router.navigate(['/login'],
      { queryParams: { error: "Deve estar logado antes do acesso " + url } });
    return false;
  }
}