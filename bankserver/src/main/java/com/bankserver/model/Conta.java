package com.bankserver.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroConta;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAprovacao;
    private BigDecimal saldo;
    private BigDecimal limite;

    @Enumerated(EnumType.STRING)
    private StatusConta statusConta; // APROVADA, PENDENTE, REJEITADA

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Saldo> historicoSaldos;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(optional = true)
    @JoinColumn(name = "gerente_id")
    private Gerente gerente;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;

    public void aprovar() {
        this.statusConta = StatusConta.APROVADA;
        this.dataAprovacao = LocalDateTime.now();
    }

    public void depositar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
        // Opcional: registrar no histórico de saldos
        // Opcional: criar uma transação do tipo DEPÓSITO
    }

    public void retirar(BigDecimal valor) {
        this.saldo = this.saldo.subtract(valor);
        // Opcional: registrar no histórico de saldos
        // Opcional: criar uma transação do tipo DEPÓSITO
    }

    public void transferir(BigDecimal valor, Conta contaDestino) {

        // verifica se o saldo suficiente considerando limite
        BigDecimal saldoDisponivel = this.saldo.add(this.limite);
        if (valor.compareTo(saldoDisponivel) > 0) {
            throw new RuntimeException("Saldo insuficiente!");
        }

        // debita da conta de origem
        this.retirar(valor);

        contaDestino.depositar(valor);
    }
}
