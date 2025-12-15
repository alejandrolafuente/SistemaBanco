package com.bankserver.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bankserver.application.domain.Conta;
import com.bankserver.application.domain.enums.TipoTransacao;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataHora;
    private BigDecimal valor;
    // Para transferências
    private String contaDestino;
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo; // DEPOSITO,SAQUE, TRANSFERENCIA
    // @ManyToOne
    // @JoinColumn(name = "conta_id")
    // private Conta conta;
}
