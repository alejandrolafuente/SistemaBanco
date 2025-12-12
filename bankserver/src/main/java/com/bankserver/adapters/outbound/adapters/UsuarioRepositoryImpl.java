package com.bankserver.adapters.outbound.adapters;

import java.util.List;

import com.bankserver.adapters.outbound.entidades.JpaUsuarioEntidade;
import com.bankserver.adapters.outbound.ports.UsuarioRepository;
import com.bankserver.adapters.outbound.repository.JpaUsuarioRepository;
import com.bankserver.application.domain.Usuario;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final JpaUsuarioRepository jpaUsuarioRepository;

    public UsuarioRepositoryImpl(JpaUsuarioRepository jpaUsuarioRepository) {
        this.jpaUsuarioRepository = jpaUsuarioRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        // Usa o método fábrica para criar a entidade JPA correta
        JpaUsuarioEntidade jpaUsuarioEntidade = JpaUsuarioEntidade.fromDomain(usuario);

        // Salva no banco
        JpaUsuarioEntidade entidadeSalva = this.jpaUsuarioRepository.save(jpaUsuarioEntidade);

        // Converte de volta para o domínio usando o método polimórfico
        return entidadeSalva.toDomain();
    }

    @Override
    public Usuario findById(Long id) {
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public boolean existsByLogin(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsByLogin'");
    }

}
