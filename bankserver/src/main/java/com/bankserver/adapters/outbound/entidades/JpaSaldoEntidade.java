package com.bankserver.adapters.outbound.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bankserver.application.domain.Saldo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "saldo")
public class JpaSaldoEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime data;
    private BigDecimal valor;
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private JpaContaEntidade conta;

    public JpaSaldoEntidade(Saldo saldo) {
        this.data = saldo.getData();
        this.valor = saldo.getValor();
        this.conta = new JpaContaEntidade(saldo.getConta());
    }

    public Saldo toDomain() {
        return new Saldo(this.id, this.data, this.valor, this.conta.toDomain());
    }

}
