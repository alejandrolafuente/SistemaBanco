package com.bankserver.dto.request;

import java.math.BigDecimal;

public record SaqueDTO(
        Long id,
        BigDecimal valor) {

}
