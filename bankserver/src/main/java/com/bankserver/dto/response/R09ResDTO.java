package com.bankserver.dto.response;

public record R09ResDTO(
        Long contaId,
        String cpf,
        String nome,
        Double salario) {
}
