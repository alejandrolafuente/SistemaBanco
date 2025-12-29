package com.bankserver.dto.response;

public record AdminResponseDTO(
        Long id,
        String cpf,
        String nome,
        String email) {

}
