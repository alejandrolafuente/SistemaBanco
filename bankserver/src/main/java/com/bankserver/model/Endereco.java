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
    private String cep;
    private String uf;
    private String cidade;
    private String bairro;
    private String rua; // Rua, Avenida, etc.
    private String numero;
    private String complemento;

    public Endereco() {
        // Construtor padrão exigido pelo Hibernate
    }
}
