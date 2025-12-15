package com.bankserver.application.domain;

import com.bankserver.model.StatusUsuario;
import com.bankserver.model.TipoUsuario;

public class Gerente extends Usuario {

    public Gerente() {

    }

    public Gerente(Long id, String cpf, String login, String nome,
            String telefone, String senha, TipoUsuario perfil,
            StatusUsuario status) {
        super(id, cpf, login, nome, telefone, senha, perfil, status);
    }

    // construtor alternativo, se necessario
    public Gerente(Usuario usuario) {
        super(usuario.getId(), usuario.getCpf(), usuario.getLogin(),
                usuario.getNome(), usuario.getTelefone(), usuario.getSenha(),
                usuario.getPerfil(), usuario.getStatus());
    }
}
