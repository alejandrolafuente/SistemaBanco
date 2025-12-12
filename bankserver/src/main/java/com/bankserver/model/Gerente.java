package com.bankserver.model;

import java.util.List;

import com.bankserver.adapters.outbound.entidades.JpaUsuarioEntidade;
import com.bankserver.application.domain.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Gerente extends JpaUsuarioEntidade {

    @OneToMany(mappedBy = "gerente")
    private List<Conta> contasGerenciadas;

    @Override
    public Usuario toDomain() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDomain'");
    }

}
