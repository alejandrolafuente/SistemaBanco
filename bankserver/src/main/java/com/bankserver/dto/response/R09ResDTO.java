package com.bankserver.dto.response;

import java.math.BigDecimal;

public record R09ResDTO(
        Long contaId,
        String cpf,
        String nome,
        BigDecimal salario) {
}
