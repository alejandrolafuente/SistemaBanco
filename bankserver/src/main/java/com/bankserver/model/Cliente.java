package com.bankserver.model;

import java.math.BigDecimal;

import com.bankserver.adapters.outbound.entidades.JpaUsuarioEntidade;
import com.bankserver.application.domain.Conta;
import com.bankserver.application.domain.Usuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Cliente extends JpaUsuarioEntidade {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    private BigDecimal salario;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Conta conta;

    @Override
    public Usuario toDomain() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDomain'");
    }
}
