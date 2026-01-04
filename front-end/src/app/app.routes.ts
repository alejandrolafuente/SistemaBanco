import { Routes } from '@angular/router';
import { LoginComponent } from './autenticacao/componentes/login/login.component';
import { AdminRoutes } from './administrador/admin-routing.module';
import { GerenteRoutes } from './gerente/gerente-routing.module';
import { ClienteRoutes } from './cliente/cliente-routing.module';
import { RegistrarClienteComponent } from './autenticacao/componentes/registrar-cliente/registrar-cliente.component';

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
        component: RegistrarClienteComponent
    },

    {
        path: 'especificacao',
        redirectTo: '/especificacao.pdf',
        pathMatch: 'full'
    },

    ...AdminRoutes,

    ...GerenteRoutes,

    ...ClienteRoutes
];
