package com.bankserver.dto.request;

public record AdminRegistrationDTO(
        @NotBlank String cpf,
        @NotBlank String email,
        @NotBlank String nome,
        @NotBlank String telefone) {

}
