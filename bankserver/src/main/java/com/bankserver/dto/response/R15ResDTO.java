package com.bankserver.dto.response;

public record R15ResDTO(
        Long id,
        String nome,
        int numeroClientes,
        int saldosPositivos,
        int saldosNegativos) {

}
