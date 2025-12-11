package com.bankserver.adapters.outbound.entidades;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class JpaAdministradorEntidade extends JpaUsuarioEntidade{
    
}
