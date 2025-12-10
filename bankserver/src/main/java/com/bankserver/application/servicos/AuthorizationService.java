package com.bankserver.application.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bankserver.adapters.outbound.repository.JpaUsuarioRepository;
import com.bankserver.model.Usuario;
import com.bankserver.seguranca.UserDetailsImpl;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private JpaUsuarioRepository usuarioRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("Log no AuthorizationService, este foi chamado pelo Manager");

        Usuario usuario = usuarioRep.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new UserDetailsImpl(usuario);
    }

}
