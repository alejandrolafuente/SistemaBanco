package com.bankserver.adapters.outbound.adapters;

import org.springframework.stereotype.Repository;

import com.bankserver.adapters.outbound.entidades.JpaClienteEntidade;
import com.bankserver.adapters.outbound.entidades.JpaEnderecoEntidade;
import com.bankserver.adapters.outbound.ports.ClienteRepository;
import com.bankserver.adapters.outbound.repository.JpaClienteRepository;
import com.bankserver.adapters.outbound.repository.JpaEnderecoRepository;
import com.bankserver.application.domain.Cliente;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final JpaClienteRepository jpaClienteRepository;

    private final JpaEnderecoRepository jpaEnderecoRepository;

    public ClienteRepositoryImpl(
            JpaClienteRepository jpaClienteRepository,
            JpaEnderecoRepository jpaEnderecoRepository) {
        this.jpaClienteRepository = jpaClienteRepository;
        this.jpaEnderecoRepository = jpaEnderecoRepository;
    }

    @Override
    public Cliente save(Cliente cliente) {

        System.out.println(" *** OBJETO CLIENTE NO ADAPTER ->" + cliente);

        JpaClienteEntidade jpaClienteEntidade = new JpaClienteEntidade(cliente);

        // carrega o endereco jah salvo no bd
        JpaEnderecoEntidade enderecoExistente = jpaEnderecoRepository
                .findById(cliente.getEndereco().getId())
                .get();

        jpaClienteEntidade.setEndereco(enderecoExistente);

        JpaClienteEntidade entidadeSalva = this.jpaClienteRepository.save(jpaClienteEntidade);
        
        return entidadeSalva.toDomain();

    }

    @Override
    public Cliente update(Cliente cliente) {
        return null;
    }

}
