package com.bankserver.model;

import com.bankserver.adapters.outbound.entidades.JpaUsuarioEntidade;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Administrador extends JpaUsuarioEntidade {

}
