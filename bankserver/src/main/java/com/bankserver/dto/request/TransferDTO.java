package com.bankserver.dto.request;

import java.math.BigDecimal;

public record TransferDTO(
        BigDecimal valor,
        String contaDestino) {
}
