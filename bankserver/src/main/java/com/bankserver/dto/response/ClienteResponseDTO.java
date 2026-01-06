package com.bankserver.dto.response;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone) {
}
