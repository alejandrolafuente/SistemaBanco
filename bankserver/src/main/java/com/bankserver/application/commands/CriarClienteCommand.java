package com.bankserver.application.commands;

import java.math.BigDecimal;

public class CriarClienteCommand {

    private final String cpf;
    private final String email;
    private final String nome;
    private final String telefone;
    private final BigDecimal salario;
    private final EnderecoValue endereco;

    public CriarClienteCommand(String cpf, String email, String nome, String telefone,
            BigDecimal salario, EnderecoValue endereco) {
        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
        this.telefone = telefone;
        this.salario = salario;
        this.endereco = endereco;
    }

    // getters
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

    public BigDecimal getSalario() {
        return salario;
    }

    public EnderecoValue getEndereco() {
        return endereco;
    }
}
