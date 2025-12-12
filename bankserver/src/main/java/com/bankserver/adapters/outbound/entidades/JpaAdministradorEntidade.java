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

    public Administrador toDomain() {
        // onverte para o objeto de dominio administrador
        return new Administrador(
            this.getId(),
            this.getCpf(),
            this.getLogin(),
            this.getNome(),
            this.getTelefone(),
            this.getSenha(),
            this.getPerfil(),
            this.getStatus()
            // adicionar aqui os atributos especificos do admin, se houver
        );
    }
}
