package com.bankserver.adapters.outbound.entidades;

import com.bankserver.application.domain.Endereco;

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

    public JpaEnderecoEntidade(Endereco endereco) {
        this.id = endereco.getId();
        this.cep = endereco.getCep();
        this.uf = endereco.getUf();
        this.cidade = endereco.getCidade();
        this.bairro = endereco.getBairro();
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
    }

    public Endereco toDomain(){
        return new Endereco(id, cep, uf, cidade, bairro, rua, numero, complemento);
    }
}
