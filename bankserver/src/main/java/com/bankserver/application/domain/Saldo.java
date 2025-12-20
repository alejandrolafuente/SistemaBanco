package com.bankserver.application.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Saldo {

    private Long id;
    private LocalDateTime data;
    private BigDecimal valor;
    private Conta conta;

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
