import { Routes } from "@angular/router";
import { AuthGuard } from "../autenticacao/servicos/auth.guard";
import { HomeComponent } from "./componentes/home/home.component";

export const ClienteRoutes: Routes = [

    {
        path: 'clientes/home/:id',
        component: HomeComponent,
        canActivate: [AuthGuard],
        data: {
            role: 'CLIENTE'
        }

    }
]