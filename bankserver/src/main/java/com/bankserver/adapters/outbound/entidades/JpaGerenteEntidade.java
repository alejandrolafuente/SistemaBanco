package com.bankserver.adapters.outbound.entidades;

import java.util.List;

import com.bankserver.application.domain.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "gerente")
@PrimaryKeyJoinColumn(name = "id")
@EqualsAndHashCode(callSuper = true)
public class JpaGerenteEntidade extends JpaUsuarioEntidade {

    @OneToMany(mappedBy = "gerente")
    private List<JpaContaEntidade> contasGerenciadas;

    @Override
    public Usuario toDomain() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDomain'");
    }

}
