package com.bankserver.application.commands;

public class CriarAdminCommand {

    private final String cpf;
    private final String email;
    private final String nome;
    private final String telefone;

    public CriarAdminCommand(String cpf, String email, String nome, String telefone) {
        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

}
