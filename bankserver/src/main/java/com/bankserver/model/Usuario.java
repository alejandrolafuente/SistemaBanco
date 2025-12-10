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

// @Data
// @Entity
// @AllArgsConstructor
// @NoArgsConstructor
// @Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Usuario() {
    }

    public Usuario(Long id, String cpf, String login, String nome, String telefone, String senha, TipoUsuario perfil,
            StatusUsuario status) {
        this.id = id;
        this.cpf = cpf;
        this.login = login;
        this.nome = nome;
        this.telefone = telefone;
        this.senha = senha;
        this.perfil = perfil;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(TipoUsuario perfil) {
        this.perfil = perfil;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

}
