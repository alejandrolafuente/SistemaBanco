package com.bankserver.application.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.bankserver.application.domain.enums.StatusConta;

public class Conta {
    private Long id;
    private String numeroConta;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAprovacao;
    private BigDecimal saldo;
    private BigDecimal limite;
    private StatusConta statusConta;
    private List<Saldo> historicoSaldos;
    private Cliente cliente;
    private Gerente gerente;
    private List<Transacao> transacoes;

    // construtores
    public Conta() {
    }

    public Conta(Long id, String numeroConta, LocalDateTime dataCriacao,
            LocalDateTime dataAprovacao, BigDecimal saldo, BigDecimal limite,
            StatusConta statusConta, List<Saldo> historicoSaldos,
            Cliente cliente, Gerente gerente, List<Transacao> transacoes) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.dataCriacao = dataCriacao;
        this.dataAprovacao = dataAprovacao;
        this.saldo = saldo;
        this.limite = limite;
        this.statusConta = statusConta;
        this.historicoSaldos = historicoSaldos;
        this.cliente = cliente;
        this.gerente = gerente;
        this.transacoes = transacoes;
    }

    // metodo gerado somente para nao dar erro, adaptar depois
    public Conta(Long id2, String numeroConta2, LocalDateTime dataCriacao2, LocalDateTime dataAprovacao2,
            BigDecimal saldo2, BigDecimal limite2, StatusConta statusConta2, Object object, Object object2,
            Object object3, Object object4) {
        //TODO Auto-generated constructor stub
    }

    // Métodos de negócio (EXATAMENTE como no original)
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

    // Getters e Setters (opcional, pode adicionar depois)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(LocalDateTime dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public StatusConta getStatusConta() {
        return statusConta;
    }

    public void setStatusConta(StatusConta statusConta) {
        this.statusConta = statusConta;
    }

    public List<Saldo> getHistoricoSaldos() {
        return historicoSaldos;
    }

    public void setHistoricoSaldos(List<Saldo> historicoSaldos) {
        this.historicoSaldos = historicoSaldos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }
    
}
