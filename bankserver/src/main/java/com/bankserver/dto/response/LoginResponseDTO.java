package com.bankserver.dto.response;

public record LoginResponseDTO(
        Long id,
        String token,
        String nome,
        String email,
        String cpf,
        String telefone,
        String perfil) {

}
