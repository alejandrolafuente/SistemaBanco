import { Routes } from "@angular/router";
import { AuthGuard } from "../autenticacao/servicos/auth.guard";
import { HomeComponent } from "./componentes/home/home.component";
import { DepositoComponent } from "./componentes/deposito/deposito.component";

export const ClienteRoutes: Routes = [

    {
        path: 'cliente/home/:id',
        component: HomeComponent,
        canActivate: [AuthGuard],
        data: {
            role: 'CLIENTE'
        }

    },
    {
        path: 'cliente/deposito',
        component: DepositoComponent,
        canActivate: [AuthGuard],
        data: {
            role: 'CLIENTE'
        }
    }
]