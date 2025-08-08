package com.bankserver.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
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
    private String tipo; // "DEPOSITO", "SAQUE", "TRANSFERENCIA"
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    // Para transferências
    private String contaDestino;
}
