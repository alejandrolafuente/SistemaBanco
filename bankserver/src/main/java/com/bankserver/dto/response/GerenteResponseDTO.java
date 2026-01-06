package com.bankserver.dto.response;

public record GerenteResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone) {
}
