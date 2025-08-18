package com.bankserver.model;

public enum TipoUsuario {
    
    CLIENTE("Cliente"),
    GERENTE("Gerente"),
    ADMIN("Administrador");

    private final String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDiescricao() {
        return descricao;
    }
}
