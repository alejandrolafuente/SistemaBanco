import { Component, ViewChild } from '@angular/core';
import { NgForm, FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterLink, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Login } from '../../../models/login/login.model';
import { LoginService } from '../../servicos/login.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    RouterLink,
    RouterModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  @ViewChild('formLogin')
  formLogin!: NgForm;

  login: Login = new Login();
  loading: boolean = false;
  message!: string;

  constructor(
    private loginService: LoginService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    // if (this.loginService.usuarioLogado) {
    //   this.router.navigate(["/login"]);
    // }
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => { this.message = params['error'] });
  }

  doLogin(): void {

    this.loading = true;

    if (this.formLogin.form.valid) {

      let observable = this.loginService.login(this.login);

      observable.subscribe(

        (usuario) => {
          if (usuario != null) {
            this.loginService.usuarioLogado = usuario; // setando Usuario na LS
            this.loading = false;
            if (usuario.perfil == "CLIENTE") {
              this.router.navigate(["/clientes/home/" + usuario.id]);
            } else if (usuario.perfil == "ADMIN") {
              this.router.navigate(["/admin/gerentes"]);
            } else if (usuario.perfil == "GERENTE") {
              this.router.navigate(["/gerentes/home/" + usuario.id]);
            }
          }
          else {
            this.message = "usuario/senha inválido(s)";
          }
        });
    }
    else { this.loading = false; }
  }
}
