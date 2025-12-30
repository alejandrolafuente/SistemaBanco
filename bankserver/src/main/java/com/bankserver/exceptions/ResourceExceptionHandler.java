package com.bankserver.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bankserver.application.domain.exceptions.UsuarioJaCadastradoException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErroPadrao> clientNotFound(ClientNotFoundException e,
            HttpServletRequest request) {

        ErroPadrao erro = ErroPadrao.builder()
                .dataErro(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant())
                .status(HttpStatus.NOT_FOUND.value())
                .erro("Cliente nao encontrado")
                .mensagem(e.getMessage())
                .caminho(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(UsuarioJaCadastradoException.class)
    public ResponseEntity<ErroPadrao> usuarioJaCadastrado(UsuarioJaCadastradoException e,
            HttpServletRequest request) {

        ErroPadrao erro = ErroPadrao.builder()
                .dataErro(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant())
                .status(HttpStatus.CONFLICT.value()) // 409 conflict pode ser + apropriado
                .erro("Usuário já cadastrado")
                .mensagem(e.getMessage())
                .caminho(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    // handler generico para RuntimeExceptions (do core)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroPadrao> runtimeException(
            RuntimeException e,
            HttpServletRequest request) {

        ErroPadrao erro = ErroPadrao.builder()
                .dataErro(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant())
                .status(HttpStatus.BAD_REQUEST.value())
                .erro("Erro de negócio")
                .mensagem(e.getMessage())
                .caminho(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
