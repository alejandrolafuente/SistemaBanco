package com.bankserver.adapters.outbound.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.bankserver.application.domain.Conta;
import com.bankserver.application.domain.enums.StatusConta;

import com.bankserver.model.Transacao;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "conta")
public class JpaContaEntidade {
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
    private List<JpaSaldoEntidade> historicoSaldos; // vai mudar para entidade jpa

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private JpaClienteEntidade cliente; // vai mudar para entidade jpa

    @ManyToOne(optional = true)
    @JoinColumn(name = "gerente_id")
    private JpaGerenteEntidade gerente; // vai mudar para entidade jpa

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Transacao> transacoes; // vai mudar para entidade jpa

    // Métodos de conversão simples
    public static JpaContaEntidade fromDomain(Conta conta) {
        if (conta == null)
            return null;

        return JpaContaEntidade.builder()
                .id(conta.getId())
                .numeroConta(conta.getNumeroConta())
                .dataCriacao(conta.getDataCriacao())
                .dataAprovacao(conta.getDataAprovacao())
                .saldo(conta.getSaldo())
                .limite(conta.getLimite())
                .statusConta(conta.getStatusConta())
                // As listas e relacionamentos serão mapeados separadamente
                .build();
    }

    public Conta toDomain() {
        return new Conta(
                this.id,
                this.numeroConta,
                this.dataCriacao,
                this.dataAprovacao,
                this.saldo,
                this.limite,
                this.statusConta,
                null, // historicoSaldos - pode ser convertido depois
                this.cliente != null ? this.cliente.toDomain() : null,
                this.gerente != null ? this.gerente.toDomain() : null,
                null // transacoes - pode ser convertido depois
        );
    }

}
