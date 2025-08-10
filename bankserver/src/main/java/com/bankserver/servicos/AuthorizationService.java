package com.bankserver.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bankserver.repository.UsuarioRep;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UsuarioRep usuarioRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRep.findByLogin(username);
    }

}
