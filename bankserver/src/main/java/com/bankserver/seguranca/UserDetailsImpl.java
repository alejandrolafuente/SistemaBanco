package com.bankserver.seguranca;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bankserver.model.StatusUsuario;
import com.bankserver.model.Usuario;

public class UserDetailsImpl implements UserDetails {

    private final Usuario usuario;

    public UserDetailsImpl(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Converte o perfil do usuário para roles (ex.: "ROLE_CLIENTE")
        return List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getPerfil().name()));
    }

    @Override
    public String getUsername() {
        return usuario.getLogin();
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return usuario.getStatus() != StatusUsuario.BLOQUEADO;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return usuario.getStatus() == StatusUsuario.ATIVO;
    }

}
