package com.bankserver.application.domain;

import java.math.BigDecimal;

import com.bankserver.application.domain.enums.StatusUsuario;
import com.bankserver.application.domain.enums.TipoUsuario;

public class Cliente extends Usuario {

    private Endereco endereco;

    private BigDecimal salario;

    private Conta conta;

    public Cliente(Long id, String cpf, String login, String nome, String telefone,
            String senha, TipoUsuario perfil, StatusUsuario status,
            BigDecimal salario) {
        super(id, cpf, login, nome, telefone, senha, perfil, status);
        this.salario = salario;
    }

    public BigDecimal getSalario() {
        return salario;
    }

}
