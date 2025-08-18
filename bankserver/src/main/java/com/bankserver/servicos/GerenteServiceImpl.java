package com.bankserver.servicos;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankserver.dto.request.GerenteRegistrationDTO;
import com.bankserver.model.Gerente;
import com.bankserver.model.StatusUsuario;
import com.bankserver.model.TipoUsuario;
import com.bankserver.repository.GerenteRep;
import com.bankserver.repository.UsuarioRep;

@Service
public class GerenteServiceImpl implements GerenteService {

    @Autowired
    private UsuarioRep usuarioRep;

    @Autowired
    private GerenteRep gerenteRep;

    @Override
    @Transactional
    public ResponseEntity<?> insertGerente(GerenteRegistrationDTO data) {

        if (this.usuarioRep.existsByLogin(data.email())) {
            return ResponseEntity.badRequest().body("Gerente já cadastrado");
        }

        Gerente gerente = new Gerente();

        String senha = generateRamdomPassword();

        gerente.setCpf(data.cpf());
        gerente.setLogin(data.email());
        gerente.setNome(data.nome());
        gerente.setTelefone(data.telefone());
        gerente.setSenha(new BCryptPasswordEncoder().encode(senha));
        gerente.setPerfil(TipoUsuario.GERENTE);
        gerente.setStatus(StatusUsuario.ATIVO);

        gerenteRep.save(gerente);

        System.out.println("SENHA NO CADASTRO GERENTE: " + senha);

        return ResponseEntity.ok().body("Gerente cadastrado com sucesso!");

    }

    private String generateRamdomPassword() {

        String CHARACTERS = "0123456789";

        int STRING_LENGTH = 4;

        SecureRandom random = new SecureRandom();

        StringBuilder sb = new StringBuilder(STRING_LENGTH);

        for (int i = 0; i < STRING_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();

    }
}
