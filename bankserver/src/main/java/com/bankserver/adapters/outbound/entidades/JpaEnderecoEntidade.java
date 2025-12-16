package com.bankserver.adapters.outbound.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "endereco")
public class JpaEnderecoEntidade {
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

    public JpaEnderecoEntidade() {
        // Construtor padrão exigido pelo Hibernate
    }
}
