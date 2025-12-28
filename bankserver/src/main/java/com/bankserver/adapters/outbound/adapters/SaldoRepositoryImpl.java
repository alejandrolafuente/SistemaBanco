package com.bankserver.adapters.outbound.adapters;

import org.springframework.stereotype.Repository;

import com.bankserver.adapters.outbound.entidades.JpaSaldoEntidade;
import com.bankserver.adapters.outbound.ports.SaldoRepository;
import com.bankserver.adapters.outbound.repository.JpaSaldoRepository;
import com.bankserver.application.domain.Saldo;

@Repository
public class SaldoRepositoryImpl implements SaldoRepository {

    private final JpaSaldoRepository jpaSaldoRepository;

    public SaldoRepositoryImpl(JpaSaldoRepository jpaSaldoRepository) {
        this.jpaSaldoRepository = jpaSaldoRepository;
    }

    @Override
    public Saldo save(Saldo saldo) {
        JpaSaldoEntidade jpaSaldoEntidade = new JpaSaldoEntidade(saldo);
        JpaSaldoEntidade entidadeSalva = this.jpaSaldoRepository.save(jpaSaldoEntidade);
        return entidadeSalva.toDomain();
    }

}
