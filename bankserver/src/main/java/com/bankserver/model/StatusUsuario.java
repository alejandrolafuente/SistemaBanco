package com.bankserver.model;

public enum StatusUsuario {

    ATIVO("Ativo"),
    INATIVO("Inativo"),
    PENDENTE("Pendente"),
    BLOQUEADO("Bloqueado");

    private final String descricao;

    StatusUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
