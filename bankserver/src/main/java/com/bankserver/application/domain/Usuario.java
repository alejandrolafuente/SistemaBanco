package com.bankserver.application.domain;

import com.bankserver.model.StatusUsuario;
import com.bankserver.model.TipoUsuario;

public abstract class Usuario {
    private Long id;
    private String cpf;
    private String login; // email
    private String nome;
    private String telefone;
    private String senha;
    private TipoUsuario perfil; // CLIENTE,GERENTE,ADMIN
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

    // * metodos de dominio
    public void ativar() {
        this.status = StatusUsuario.ATIVO;
    }

    public void bloquear() {
        this.status = StatusUsuario.BLOQUEADO;
    }

    public void alterarSenha(String novaSenha) {
        this.senha = novaSenha;
    }

    public boolean isAtivo() {
        return StatusUsuario.ATIVO.equals(this.status);
    }

}
