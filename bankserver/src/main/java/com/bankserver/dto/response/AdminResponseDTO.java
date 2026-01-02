package com.bankserver.dto.response;

public record AdminResponseDTO(
                Long id,
                String nome,
                String cpf,
                String email,
                String telefone) {
}
