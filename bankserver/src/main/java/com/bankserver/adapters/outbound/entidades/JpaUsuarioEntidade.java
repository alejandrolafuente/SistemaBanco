package com.bankserver.adapters.outbound.entidades;

import com.bankserver.application.domain.Usuario;
import com.bankserver.model.StatusUsuario;
import com.bankserver.model.TipoUsuario;

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
public abstract class JpaUsuarioEntidade {
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

    public JpaUsuarioEntidade(Usuario usuario) {
        this.id = usuario.getId();
        this.cpf = usuario.getCpf();
        this.login = usuario.getLogin();
        this.nome = usuario.getNome();
        this.telefone = usuario.getTelefone();
        this.senha = usuario.getSenha();
        this.perfil = usuario.getPerfil();
        this.status = usuario.getStatus();
    }

}
