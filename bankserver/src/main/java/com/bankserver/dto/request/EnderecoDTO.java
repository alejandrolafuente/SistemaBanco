package com.bankserver.dto.request;

public record EnderecoDTO(
        @NotBlank String cep,
        @NotBlank String uf,
        @NotBlank String cidade,
        @NotBlank String bairro,
        @NotBlank String rua,
        @NotBlank String numero,
        String complemento) {
}
