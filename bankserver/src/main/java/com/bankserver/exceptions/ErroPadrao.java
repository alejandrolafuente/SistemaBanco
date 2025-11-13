package com.bankserver.exceptions;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErroPadrao implements Serializable {
    private Instant dataErro;
    private Integer status;
    private String erro;
    private String mensagem;
    private String caminho;
}
