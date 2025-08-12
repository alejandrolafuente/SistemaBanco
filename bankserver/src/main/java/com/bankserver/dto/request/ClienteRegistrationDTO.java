package com.bankserver.dto.request;

public record ClienteRegistrationDTO(
                @NotBlank String cpf,
                @NotBlank String email,
                @NotBlank String nome,
                @NotBlank String telefone,
                Double salario,
                @Valid EnderecoDTO endereco) {
}
