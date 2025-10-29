package com.bankserver.dto.request;

import java.math.BigDecimal;

public record DepositoDTO(
        Long id,
        BigDecimal valor) {
}
