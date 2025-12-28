package com.bankserver.application.domain;

import com.bankserver.application.domain.enums.StatusUsuario;
import com.bankserver.application.domain.enums.TipoUsuario;

public class Administrador extends Usuario {

    public Administrador() {
    };

    public Administrador(Long id, String cpf, String login, String nome,
            String telefone, String senha, TipoUsuario perfil,
            StatusUsuario status) {
        super(id, cpf, login, nome, telefone, senha, perfil, status);
    }

    // construtor alternativo, se necessario
    public Administrador(Usuario usuario) {
        super(usuario.getId(), usuario.getCpf(), usuario.getLogin(),
                usuario.getNome(), usuario.getTelefone(), usuario.getSenha(),
                usuario.getPerfil(), usuario.getStatus());
    }
}
