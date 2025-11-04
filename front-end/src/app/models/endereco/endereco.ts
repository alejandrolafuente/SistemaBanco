export interface Endereco {
    cep: string;
    uf: string;
    cidade: string;
    bairro: string;
    rua: string;
    numero: string;
    complemento?: string; // opcional
}
