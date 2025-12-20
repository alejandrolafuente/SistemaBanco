package com.bankserver.adapters.outbound.adapters;

import org.springframework.stereotype.Repository;

import com.bankserver.adapters.outbound.entidades.JpaClienteEntidade;
import com.bankserver.adapters.outbound.ports.ClienteRepository;
import com.bankserver.adapters.outbound.repository.JpaClienteRepository;
import com.bankserver.application.domain.Cliente;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final JpaClienteRepository jpaClienteRepository;

    public ClienteRepositoryImpl(JpaClienteRepository jpaClienteRepository) {
        this.jpaClienteRepository = jpaClienteRepository;
    }

    @Override
    public Cliente save(Cliente cliente) {

        JpaClienteEntidade jpaClienteEntidade = new JpaClienteEntidade(cliente);
        JpaClienteEntidade entidadeSalva = this.jpaClienteRepository.save(jpaClienteEntidade);
        return entidadeSalva.toDomain();
        
    }

}
