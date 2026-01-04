package com.bankserver.application.commands;

import com.bankserver.dto.request.EnderecoDTO;

public class EnderecoValue {

    private final String cep;
    private final String uf;
    private final String cidade;
    private final String bairro;
    private final String rua;
    private final String numero;
    private final String complemento;

    public EnderecoValue(String cep, String uf, String cidade, String bairro,
            String rua, String numero, String complemento) {
        this.cep = cep;
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    // getters

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

    // opcional: metodo estatico para criar a partir do DTO
    public static EnderecoValue fromDTO(EnderecoDTO dto) {
        return new EnderecoValue(
                dto.cep(),
                dto.uf(),
                dto.cidade(),
                dto.bairro(),
                dto.rua(),
                dto.numero(),
                dto.complemento());
    }

}
