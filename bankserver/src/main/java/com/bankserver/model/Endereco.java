package com.bankserver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoLogradouro; // Rua, Avenida, etc.
    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;
}
