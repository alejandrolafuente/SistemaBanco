package com.bankserver.model;

import java.util.List;

public interface UsuarioRepository {// * PORTA!

    Usuario save(Usuario usuario);

    Usuario findById(Long id);

    List<Usuario> findAll();

    void deleteById(Long id);

    boolean existsByLogin(String email);

}
