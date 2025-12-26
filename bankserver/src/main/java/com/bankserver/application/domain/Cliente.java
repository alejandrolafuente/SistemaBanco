package com.bankserver.application.domain;

import java.math.BigDecimal;

import com.bankserver.application.domain.enums.StatusUsuario;
import com.bankserver.application.domain.enums.TipoUsuario;

public class Cliente extends Usuario {

    private Endereco endereco;

    private BigDecimal salario;

    private Conta conta;

    public Cliente() {
    }

    public Cliente(Long id, String cpf, String login, String nome, String telefone,
            String senha, TipoUsuario perfil, StatusUsuario status,
            BigDecimal salario) {
        super(id, cpf, login, nome, telefone, senha, perfil, status);
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + getId() +
                ", cpf='" + getCpf() + '\'' +
                ", email='" + getLogin() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", perfil=" + getPerfil() +
                ", status=" + getStatus() +
                ", salario=" + salario +
                ", endereco=" + endereco +
                '}'; //incluir conta depois
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

}
