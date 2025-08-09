import { Routes } from '@angular/router';
import { LoginComponent } from './autenticacao/componentes/login/login.component';

export const routes: Routes = [

    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    },

    {
        path: 'login',
        component: LoginComponent
    }
];
