package com.bankserver.dto.request;

public record GerenteRegistrationDTO(
        @NotBlank String cpf,
        @NotBlank String email,
        @NotBlank String nome,
        @NotBlank String telefone) {

}
