package com.bankserver.dto.request;

public record ClienteRegistrationDTO(

        @NotBlank String nome,
        @NotBlank String cpf,
        @NotBlank String email,
        @NotBlank String telefone,
        @NotBlank String senha,
        @NotBlank Double salario,
        @Valid EnderecoDTO endereco) {
}
