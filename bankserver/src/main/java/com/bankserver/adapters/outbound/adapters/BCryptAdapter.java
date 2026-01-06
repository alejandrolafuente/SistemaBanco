package com.bankserver.adapters.outbound.adapters;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.bankserver.adapters.outbound.ports.HashSenhaPort;

@Component
public class BCryptAdapter implements HashSenhaPort {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String hash(String senha) {
        
        return encoder.encode(senha);
    }

}
