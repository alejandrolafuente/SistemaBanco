package com.bankserver.dto.request;

import java.math.BigDecimal;

public record ClienteRegistrationDTO(
        @NotBlank String cpf,
        @NotBlank String email,
        @NotBlank String nome,
        @NotBlank String telefone,
        BigDecimal salario,
        @Valid EnderecoDTO endereco) {
}
