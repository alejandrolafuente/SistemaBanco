package com.bankserver.adapters.outbound.adapters;

import org.springframework.stereotype.Repository;

import com.bankserver.adapters.outbound.entidades.JpaContaEntidade;
import com.bankserver.adapters.outbound.ports.ContaRepository;
import com.bankserver.adapters.outbound.repository.JpaContaRepository;
import com.bankserver.application.domain.Conta;

@Repository
public class ContaRepositoryImpl implements ContaRepository {

    private final JpaContaRepository jpaContaRepository;

    public ContaRepositoryImpl(JpaContaRepository jpaContaRepository) {
        this.jpaContaRepository = jpaContaRepository;
    }

    @Override
    public Conta save(Conta conta) {

        JpaContaEntidade jpaContaEntidade = new JpaContaEntidade(conta);
        // JpaClienteEntidade jpaClienteEntidade = new JpaClienteEntidade();
        // JpaClienteEntidade entidadeSalva = this.jpaClienteRepository.save(jpaClienteEntidade);
        // return entidadeSalva.toDomain();
        return null;
    }

}
