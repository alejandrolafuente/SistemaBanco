package com.bankserver.application.servicos;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankserver.adapters.outbound.ports.AdminRepository;
import com.bankserver.adapters.outbound.ports.UsuarioRepository;
import com.bankserver.adapters.outbound.repository.GerenteRep;
import com.bankserver.application.domain.Administrador;
import com.bankserver.application.usecases.AdminService;
import com.bankserver.dto.request.AdminRegistrationDTO;
import com.bankserver.dto.request.GerenteRegistrationDTO;
import com.bankserver.model.Gerente;
import com.bankserver.model.StatusUsuario;
import com.bankserver.model.TipoUsuario;
import com.bankserver.utils.ServicoEmail;

@Service
public class AdminServiceImpl implements AdminService {

    // @Autowired
    // private JpaUsuarioRepository usuarioRep;

    private final UsuarioRepository usuarioRepository = null;

    private final AdminRepository adminRepository = null;

    @Autowired
    private GerenteRep gerenteRep;

    @Autowired
    private ServicoEmail servicoEmail;

    // R17
    @Override
    @Transactional
    public ResponseEntity<Void> insertGerente(GerenteRegistrationDTO data) {

        if (this.usuarioRepository.existsByLogin(data.email())) {
            // return ResponseEntity.badRequest().body("Gerente já cadastrado");
            return ResponseEntity.badRequest().build();
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

        gerente = gerenteRep.save(gerente);

        String subject = "BANTADS: CADASTRO DE GERENTE APROVADO";

        String message = "Seu cadastro como gerente foi aprovado, sua senha é " + senha;

        System.out.println("SENHA NO CADASTRO GERENTE: " + senha);

        servicoEmail.sendApproveEmail(gerente.getLogin(), subject, message);

        // return ResponseEntity.ok(new GerRegResDTO(gerente.getId(), gerente.getCpf(),
        // gerente.getLogin(), gerente.getNome(), gerente.getTelefone()));
        return ResponseEntity.ok().build();

    }

    // R21 - Cadastrar ADMIN
    @Override
    @Transactional
    public ResponseEntity<Void> insertAdmin(AdminRegistrationDTO data) {

        if (this.usuarioRepository.existsByLogin(data.email())) {
            // return ResponseEntity.badRequest().body("Administrador já cadastrado!");
            return ResponseEntity.badRequest().build(); // remove o body por enquanto
        }

        Administrador administrador = new Administrador();
        String senha = generateRamdomPassword();

        administrador.setCpf(data.cpf());
        administrador.setLogin(data.email());
        administrador.setNome(data.nome());
        administrador.setTelefone(data.telefone());
        administrador.setSenha(new BCryptPasswordEncoder().encode(senha));
        administrador.setPerfil(TipoUsuario.ADMIN);
        administrador.setStatus(StatusUsuario.ATIVO);

        adminRepository.save(administrador);

        String subject = "BANTADS: CADASTRO DE ADMINISTADOR APROVADO";

        String message = "Seu cadastro como administrador foi aprovado, sua senha é " + senha;

        System.out.println("SENHA NO CADASTRO ADMIN: " + senha);

        servicoEmail.sendApproveEmail(administrador.getLogin(), subject, message);

        // return ResponseEntity.ok()
        // .body("Administrador cadastrado com sucesso! Veja seu email: " +
        // administrador.getLogin());
        return ResponseEntity.ok().build();
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
