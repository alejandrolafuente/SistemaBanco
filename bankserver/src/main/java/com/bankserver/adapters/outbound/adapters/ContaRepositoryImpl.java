package com.bankserver.adapters.outbound.adapters;

import java.util.Optional;

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

    // R01
    @Override
    public Conta save(Conta conta) {

        JpaContaEntidade jpaContaEntidade = new JpaContaEntidade(conta);
        JpaContaEntidade contaJpaSalva = this.jpaContaRepository.save(jpaContaEntidade);
        return contaJpaSalva.toDomain();
    }

    // R10
    @Override
    public Conta update(Conta conta) {

        JpaContaEntidade jpaConta = jpaContaRepository.findById(conta.getId())
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        // atualiza apenas campos necessarios
        jpaConta.setStatusConta(conta.getStatusConta());
        jpaConta.setDataAprovacao(conta.getDataAprovacao());
        jpaConta.setSaldo(conta.getSaldo());

        JpaContaEntidade atualizada = jpaContaRepository.save(jpaConta);
        return atualizada.toDomain();
    }

    @Override
    public Optional<Conta> findById(Long contaId) {
        return null;
    }

}
