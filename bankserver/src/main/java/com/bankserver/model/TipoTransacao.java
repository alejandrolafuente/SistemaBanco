package com.bankserver.model;

public enum TipoTransacao {

    DEPOSITO("Depósito"),
    SAQUE("Saque"),
    TRANSFERENCIA("Transferenciai");

    private final String descricao;

    TipoTransacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
