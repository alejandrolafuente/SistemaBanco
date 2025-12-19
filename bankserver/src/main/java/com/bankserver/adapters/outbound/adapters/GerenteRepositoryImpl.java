package com.bankserver.adapters.outbound.adapters;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.bankserver.adapters.outbound.entidades.JpaGerenteEntidade;
import com.bankserver.adapters.outbound.ports.GerenteRepository;
import com.bankserver.adapters.outbound.repository.JpaGerenteRepository;
import com.bankserver.application.domain.Gerente;

@Repository
public class GerenteRepositoryImpl implements GerenteRepository {

    private final JpaGerenteRepository jpaGerenteRepository;

    public GerenteRepositoryImpl(JpaGerenteRepository jpaGerenteRepository) {
        this.jpaGerenteRepository = jpaGerenteRepository;
    }

    @Override
    public Gerente save(Gerente gerente) {

        JpaGerenteEntidade jpaGerenteEntidade = new JpaGerenteEntidade(gerente);

        // Salva no banco
        JpaGerenteEntidade entidadeSalva = this.jpaGerenteRepository.save(jpaGerenteEntidade);

        return (Gerente) entidadeSalva.toDomain();
    }

    @Override
    public List<Gerente> findAllOrderByQuantidadeContas() {
        
        Pageable limit = PageRequest.of(0, 1);

        return null;
    }
    
}
