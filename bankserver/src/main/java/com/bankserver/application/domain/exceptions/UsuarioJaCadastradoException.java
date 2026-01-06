package com.bankserver.application.domain.exceptions;

public class UsuarioJaCadastradoException extends RuntimeException {

    public UsuarioJaCadastradoException(String message) {
        super(message);
    }

    public UsuarioJaCadastradoException(String email, String tipoUsuario) {
        super(String.format("%s já cadastrado com email: %s", tipoUsuario, email));
    }
}
