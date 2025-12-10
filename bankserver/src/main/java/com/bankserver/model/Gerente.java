package com.bankserver.model;

import java.util.List;

import com.bankserver.adapters.outbound.entidades.JpaUsuarioEntidade;

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

}
