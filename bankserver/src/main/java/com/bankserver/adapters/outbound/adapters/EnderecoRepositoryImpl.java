package com.bankserver.adapters.outbound.adapters;

import org.springframework.stereotype.Repository;

import com.bankserver.adapters.outbound.entidades.JpaEnderecoEntidade;
import com.bankserver.adapters.outbound.ports.EnderecoRepository;
import com.bankserver.adapters.outbound.repository.JpaEnderecoRepository;
import com.bankserver.application.domain.Endereco;

@Repository
public class EnderecoRepositoryImpl implements EnderecoRepository {

    private final JpaEnderecoRepository jpaEnderecoRepository;

    public EnderecoRepositoryImpl(JpaEnderecoRepository jpaEnderecoRepository) {
        this.jpaEnderecoRepository = jpaEnderecoRepository;
    }

    @Override
    public Endereco save(Endereco endereco) {

        JpaEnderecoEntidade jpaEnderecoEntidade = new JpaEnderecoEntidade(endereco);

        JpaEnderecoEntidade enderecoSalvo = this.jpaEnderecoRepository.save(jpaEnderecoEntidade);

        return enderecoSalvo.toDomain();

    }

}
