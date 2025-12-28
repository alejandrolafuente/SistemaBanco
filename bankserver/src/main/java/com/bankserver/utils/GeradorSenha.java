package com.bankserver.utils;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class GeradorSenha {

    private static final String CHARACTERS = "0123456789";
    private static final int DEFAULT_PASSWORD_LENGTH = 4;

    private final SecureRandom secureRandom;

    public GeradorSenha() {
        this.secureRandom = new SecureRandom();
    }

    public String gerarSenhaAleatoria() {
        return gerarSenhaAleatoria(DEFAULT_PASSWORD_LENGTH);
    }

    public String gerarSenhaAleatoria(int length) {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

}
