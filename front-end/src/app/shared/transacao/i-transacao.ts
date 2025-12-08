import { Usuario } from "../../models/usuario/usuario";

export interface ITransacao {

    // propriedades comuns
    saldo: number;
    limite: number;
    valorTransacao: number;
    usuario: Usuario | null;
    erroMensagem: string;

    // busca saldo na classe abstrata
    ngOnInit(): void;

    // busca saldo e limite para a tela inicial da transacao 
    // preciso buscar alem do saldo e limite o numero da conta
    buscaSaldo(): void;

    // temos que validar transacao,
    // relacao saldo-transacao no saque e transferencia
    validarTransacao(): boolean;

    // mostrar tela para que a transacao seja confirmada pelo usuario
    // aqui temos que implementar um metodo que procure no servidor/LS (ver login)
    // dados da conta destino para transferencia: nome, numero da conta, cpf
    mostrarTelaConfirmacaoTransacao(): void;

    // template method - metodo chave do design pattern template method
    executarTransacao(): void;

    redirecionar(): void
}
