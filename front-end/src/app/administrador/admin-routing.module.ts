import { Routes } from "@angular/router";
import { AuthGuard } from "../autenticacao/servicos/auth.guard";
import { GerentesComponent } from "./componentes/gerentes/gerentes.component";
import { InsercaoGerenteComponent } from "./componentes/insercao-gerente/insercao-gerente.component";
import { ListagemGerentesComponent } from "./componentes/listagem-gerentes/listagem-gerentes.component";
import { RelatorioClientesComponent } from "./componentes/relatorio-clientes/relatorio-clientes.component";

export const AdminRoutes: Routes = [

    {
        path: 'admin/gerentes',
        component: GerentesComponent,
        canActivate: [AuthGuard],
        data: {
            role: 'ADMIN'
        }

    }
]