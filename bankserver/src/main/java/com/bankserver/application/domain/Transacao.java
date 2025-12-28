package com.bankserver.application.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bankserver.application.domain.enums.TipoTransacao;

public class Transacao {

    private Long id;
    private LocalDateTime dataHora;
    private BigDecimal valor;
    // para transferencias
    private String contaDestino;
    private TipoTransacao tipo; // Enum: DEPOSITO,SAQUE,TRANSFERENCIA
    private Conta conta;

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", dataHora=" + dataHora +
                ", valor=" + valor +
                ", tipo=" + tipo +
                '}';
    }
}
