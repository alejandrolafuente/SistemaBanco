import { Routes } from "@angular/router";
import { AuthGuard } from "../autenticacao/servicos/auth.guard";
import { HomeComponent } from "./componentes/home/home.component";
import { DepositoComponent } from "./componentes/deposito/deposito.component";
import { SaqueComponent } from "./componentes/saque/saque.component";
import { TransferenciaComponent } from "./componentes/transferencia/transferencia.component";

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
    },
    {
        path: 'cliente/saque',
        component: SaqueComponent,
        canActivate: [AuthGuard],
        data: {
            role: 'CLIENTE'
        }
    },
    {
        path: 'cliente/transferencia',
        component: TransferenciaComponent,
        canActivate: [AuthGuard],
        data: {
            role: 'CLIENTE'
        }
    }
]