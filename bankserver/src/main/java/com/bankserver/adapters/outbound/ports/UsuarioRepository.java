package com.bankserver.adapters.outbound.ports;

import java.util.List;

import com.bankserver.model.Usuario;

public interface UsuarioRepository {// * PORTA!

    Usuario save(Usuario usuario);

    Usuario findById(Long id);

    List<Usuario> findAll();

    void deleteById(Long id);

    boolean existsByLogin(String email);

}
