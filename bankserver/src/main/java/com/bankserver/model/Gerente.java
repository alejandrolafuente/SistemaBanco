package com.bankserver.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Gerente extends Usuario {

    @OneToMany(mappedBy = "gerente")
    private List<Conta> contasGerenciadas;

}
