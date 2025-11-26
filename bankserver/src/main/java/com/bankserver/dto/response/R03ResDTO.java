package com.bankserver.dto.response;

import java.math.BigDecimal;

public record R03ResDTO(
        BigDecimal saldo,
        BigDecimal limite) {
}
