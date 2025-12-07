import { Usuario } from "../../models/usuario/usuario";

export interface ITransacao {

    // propriedades comuns
    saldo: number;
    limite: number;
    valorTransacao: number;
    usuario: Usuario | null;
    erroMensagem: string;

    // metodos obrigatorios
    ngOnInit(): void;
    buscaSaldo(): void;
    validarTransacao(): boolean;

    // template method - metodo chave do design pattern template method
    executarTransacao(): void;

    redirecionar(): void
}
