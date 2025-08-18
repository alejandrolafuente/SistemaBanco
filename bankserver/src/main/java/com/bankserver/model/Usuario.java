package com.bankserver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String login; // email
    private String nome;
    private String telefone;
    private String senha;
    @Enumerated(EnumType.STRING)
    private TipoUsuario perfil; // CLIENTE,GERENTE,ADMIN
    @Enumerated(EnumType.STRING)
    private StatusUsuario status; // ATIVO,INATIVO,PENDENTE,BLOQUEADO

}
