import { Routes } from "@angular/router";
import { AuthGuard } from "../autenticacao/servicos/auth.guard";
import { GerentesComponent } from "./componentes/gerentes/gerentes.component";
import { InsercaoGerenteComponent } from "./componentes/insercao-gerente/insercao-gerente.component";
import { ListagemGerentesComponent } from "./componentes/listagem-gerentes/listagem-gerentes.component";
import { RelatorioClientesComponent } from "./componentes/relatorio-clientes/relatorio-clientes.component";
import { RegistrarAdminComponent } from "./componentes/registrar-admin/registrar-admin.component";

export const AdminRoutes: Routes = [
    {
        path: 'admin/registrar',
        component: RegistrarAdminComponent
    },
    {
        path: 'admin/home',
        component: GerentesComponent,
        canActivate: [AuthGuard],
        data: {
            role: 'ADMIN'
        }

    },
    {
        path: 'admin/listar-gerentes',
        component: ListagemGerentesComponent,
        canActivate: [AuthGuard],
        data: {
            role: 'ADMIN'
        }

    },
    {
        path: 'admin/inserir-gerente',
        component: InsercaoGerenteComponent,
        canActivate: [AuthGuard],
        data: {
            role: 'ADMIN'
        }

    }
]   