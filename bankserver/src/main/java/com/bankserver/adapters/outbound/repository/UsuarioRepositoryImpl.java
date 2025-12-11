package com.bankserver.adapters.outbound.repository;

import java.util.List;

import com.bankserver.adapters.outbound.entidades.JpaUsuarioEntidade;
import com.bankserver.model.Usuario;
import com.bankserver.model.UsuarioRepository;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final JpaUsuarioRepository jpaUsuarioRepository;

    public UsuarioRepositoryImpl(JpaUsuarioRepository jpaUsuarioRepository) {
        this.jpaUsuarioRepository = jpaUsuarioRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        JpaUsuarioEntidade jpaUsuarioEntidade = new JpaUsuarioEntidade(usuario);
        this.jpaUsuarioRepository.save(jpaUsuarioEntidade);
        return null;
        // return new Usuario(
        //         jpaUsuarioEntidade.getId(),
        //         jpaUsuarioEntidade.getCpf(),
        //         jpaUsuarioEntidade.getLogin(),
        //         jpaUsuarioEntidade.getNome(),
        //         jpaUsuarioEntidade.getTelefone(),
        //         jpaUsuarioEntidade.getSenha(),
        //         jpaUsuarioEntidade.getPerfil(),
        //         jpaUsuarioEntidade.getStatus());
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
