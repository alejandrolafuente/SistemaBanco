package com.bankserver.model;

public enum StatusConta {

    APROVADA("Aprovada"),
    PENDENTE("Pendente"),
    REJEITADA("Rejeitada");

    private final String descricao;

    StatusConta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
