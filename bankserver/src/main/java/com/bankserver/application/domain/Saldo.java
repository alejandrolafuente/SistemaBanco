package com.bankserver.application.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Saldo {

    private Long id;
    private LocalDateTime data;
    private BigDecimal valor;
    private Conta conta;

    public Saldo() {
    }

    public Saldo(Long id, LocalDateTime data, BigDecimal valor, Conta conta) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.conta = conta;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Conta getConta() {
        return conta;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

}
