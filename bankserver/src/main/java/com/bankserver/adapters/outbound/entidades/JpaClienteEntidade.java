package com.bankserver.adapters.outbound.entidades;

import java.math.BigDecimal;

import com.bankserver.application.domain.Cliente;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "id")
@EqualsAndHashCode(callSuper = true)
public class JpaClienteEntidade extends JpaUsuarioEntidade {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private JpaEnderecoEntidade endereco; // *mudar p/ jpa */

    private BigDecimal salario;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private JpaContaEntidade conta;

    public JpaClienteEntidade() {
        super();
    }

    public JpaClienteEntidade(Cliente cliente) {
        super(cliente);
        this.salario = cliente.getSalario();
    }

    @Override
    public Cliente toDomain() {

        Cliente cliente = new Cliente(
                this.getId(),
                this.getCpf(),
                this.getLogin(),
                this.getNome(),
                this.getTelefone(),
                this.getSenha(),
                this.getPerfil(),
                this.getStatus(),
                this.getSalario());

        cliente.setEndereco(this.endereco.toDomain());

        return cliente;

    }

}
