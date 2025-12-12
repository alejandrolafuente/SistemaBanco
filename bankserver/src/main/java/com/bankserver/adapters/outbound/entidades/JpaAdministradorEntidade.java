package com.bankserver.adapters.outbound.entidades;

import com.bankserver.application.domain.Administrador;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class JpaAdministradorEntidade extends JpaUsuarioEntidade {

    public JpaAdministradorEntidade() {
        super();
    }

    public JpaAdministradorEntidade(Administrador administrador) {
        super(administrador);
    }

}
