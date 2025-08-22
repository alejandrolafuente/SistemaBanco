package com.bankserver.dto.response;

public record GerRegResDTO(
        Long id,
        String cpf,
        String email,
        String nome,
        String telefone) {

}
