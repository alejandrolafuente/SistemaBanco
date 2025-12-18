package com.bankserver.application.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Saldo {

    private Long id;
    private LocalDateTime data;
    private BigDecimal valor;
    private Conta conta;

}
