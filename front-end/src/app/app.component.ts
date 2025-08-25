import { Component } from '@angular/core';
import { RouterOutlet, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { LoginService } from './autenticacao/servicos/login.service';
import { Usuario } from './models/usuario/usuario.model';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  title = 'BANTADS';

  constructor(
    private router: Router,
    private loginService: LoginService
  ) { }

  get loggedUser(): Usuario | null {
    return this.loginService.usuarioLogado;
  }

  logout() {
    this.loginService.logout();
    this.router.navigate(['/login']);
  }
}
