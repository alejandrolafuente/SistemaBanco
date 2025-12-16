package com.bankserver.adapters.outbound.entidades;

import java.util.List;

import com.bankserver.application.domain.Gerente;
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

    public JpaGerenteEntidade() {
        super();
    }

    public JpaGerenteEntidade(Gerente gerente) {
        super(gerente);
    }

    @Override
    public Usuario toDomain() {
        // converte para o objeto de dominio gerente
        return new Gerente(
                this.getId(),
                this.getCpf(),
                this.getLogin(),
                this.getNome(),
                this.getTelefone(),
                this.getSenha(),
                this.getPerfil(),
                this.getStatus());
    }

}
