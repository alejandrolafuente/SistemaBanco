package com.bankserver.application.domain;

public class Endereco {
    private Long id;
    private String cep;
    private String uf;
    private String cidade;
    private String bairro;
    private String rua; // Rua, Avenida, etc.
    private String numero;
    private String complemento;
    
    public Endereco() {
    }

    public Endereco(Long id, String cep, String uf, String cidade, String bairro, String rua, String numero,
            String complemento) {
        this.id = id;
        this.cep = cep;
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Long getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public String getUf() {
        return uf;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

}
