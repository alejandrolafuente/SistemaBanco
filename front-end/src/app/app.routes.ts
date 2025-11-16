import { Routes } from '@angular/router';
import { LoginComponent } from './autenticacao/componentes/login/login.component';
import { CadastroComponent } from './autenticacao/componentes/cadastro/cadastro.component';
import { AdminRoutes } from './administrador/admin-routing.module';
import { GerenteRoutes } from './gerente/gerente-routing.module';
import { ClienteRoutes } from './cliente/cliente-routing.module';
import { Cadastro2Component } from './autenticacao/componentes/cadastro2/cadastro2.component';

export const routes: Routes = [

    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    },

    {
        path: 'login',
        component: LoginComponent
    },

    {
        path: 'cadastro',
        component: CadastroComponent
    },


    {
        path: 'cadastro2',
        component: Cadastro2Component
    },

    ...AdminRoutes,

    ...GerenteRoutes,

    ...ClienteRoutes
];
