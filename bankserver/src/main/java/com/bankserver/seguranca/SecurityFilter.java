package com.bankserver.seguranca;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bankserver.model.Usuario;
import com.bankserver.repository.UsuarioRep;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRep usuarioRep;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = this.recoverToken(request);

        if (token != null) {
            var login = tokenService.validateToken(token);

            Usuario usuario = usuarioRep.findByLogin(login)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

            // UserDetails user = new UserDetailsImpl(usuario);
            UserDetails userDetails = new UserDetailsImpl(usuario);

            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    // @Override
    // protected void doFilterInternal(HttpServletRequest request,
    // HttpServletResponse response, FilterChain filterChain)
    // throws ServletException, IOException {

    // System.out.println("=== REQUISIÇÃO: " + request.getRequestURI() + " ===");

    // var token = this.recoverToken(request);
    // System.out.println("TOKEN RECUPERADO: " + token);

    // if (token != null) {
    // try {
    // var login = tokenService.validateToken(token);
    // System.out.println("LOGIN DO TOKEN: " + login);

    // Usuario usuario = usuarioRep.findByLogin(login)
    // .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

    // System.out.println("USUÁRIO ENCONTRADO: " + usuario.getNome());
    // System.out.println("PERFIL: " + usuario.getPerfil());

    // UserDetails user = new UserDetailsImpl(usuario);
    // var authentication = new UsernamePasswordAuthenticationToken(user, null,
    // user.getAuthorities());
    // SecurityContextHolder.getContext().setAuthentication(authentication);

    // System.out.println("AUTENTICAÇÃO CRIADA: " +
    // authentication.getAuthorities());

    // } catch (Exception e) {
    // System.out.println("ERRO AO VALIDAR TOKEN: " + e.getMessage());
    // }
    // } else {
    // System.out.println("NENHUM TOKEN ENCONTRADO!");
    // }

    // filterChain.doFilter(request, response);
    // }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null)
            return null;
        return authHeader.replace("Bearer ", "");
    }
}
