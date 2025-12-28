package com.bankserver.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bankserver.adapters.outbound.entidades.JpaUsuarioEntidade;
import com.bankserver.adapters.outbound.repository.JpaUsuarioRepository;
import com.bankserver.application.domain.Usuario;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private JpaUsuarioRepository usuarioRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("Log no AuthorizationService, este foi chamado pelo SS Manager");

        // retorna JpaUsuarioEntidade
        JpaUsuarioEntidade jpaUsuario = usuarioRep.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // converte ao dominio
        Usuario usuario = jpaUsuario.toDomain();

        return new UserDetailsImpl(usuario);
    }

}
