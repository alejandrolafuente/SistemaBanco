package com.bankserver.application.servicos;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bankserver.adapters.outbound.ports.AdminRepository;
import com.bankserver.adapters.outbound.ports.EmailServicePort;
import com.bankserver.adapters.outbound.ports.GerenteRepository;
import com.bankserver.adapters.outbound.ports.UsuarioRepository;

import com.bankserver.application.domain.Administrador;
import com.bankserver.application.usecases.AdminService;
import com.bankserver.dto.request.AdminRegistrationDTO;
import com.bankserver.dto.request.GerenteRegistrationDTO;
import com.bankserver.utils.GeradorSenha;
import com.bankserver.application.domain.Gerente;
import com.bankserver.application.domain.enums.StatusUsuario;
import com.bankserver.application.domain.enums.TipoUsuario;

@Service
public class AdminServiceImpl implements AdminService {

    private final UsuarioRepository usuarioRepository;

    private final AdminRepository adminRepository;

    private final GerenteRepository gerenteRepository;

    private final EmailServicePort emailServicePort;

    private final GeradorSenha geradorSenha;

    // injecao via construtor - seguindo Hexagonal
    public AdminServiceImpl(UsuarioRepository usuarioRepository,
            AdminRepository adminRepository,
            GerenteRepository gerenteRepository,
            EmailServicePort emailServicePort,
            GeradorSenha geradorSenha) {
        this.usuarioRepository = usuarioRepository;
        this.adminRepository = adminRepository;
        this.gerenteRepository = gerenteRepository;
        this.emailServicePort = emailServicePort;
        this.geradorSenha = geradorSenha;
    }

    // R17
    @Override
    public ResponseEntity<Void> insertGerente(GerenteRegistrationDTO data) {

        if (this.usuarioRepository.existsByLogin(data.email())) {
            // return ResponseEntity.badRequest().body("Gerente já cadastrado");
            return ResponseEntity.badRequest().build();
        }

        Gerente gerente = new Gerente();

        String senha = geradorSenha.gerarSenhaAleatoria();

        gerente.setCpf(data.cpf());
        gerente.setLogin(data.email());
        gerente.setNome(data.nome());
        gerente.setTelefone(data.telefone());
        gerente.setSenha(new BCryptPasswordEncoder().encode(senha));
        gerente.setPerfil(TipoUsuario.GERENTE);
        gerente.setStatus(StatusUsuario.ATIVO);

        gerenteRepository.save(gerente);

        String subject = "BANTADS: CADASTRO DE GERENTE APROVADO";

        String message = "Seu cadastro como gerente foi aprovado, sua senha é " + senha;

        System.out.println("SENHA NO CADASTRO GERENTE: " + senha);

        emailServicePort.sendApproveEmail(gerente.getLogin(), subject, message);

        // return ResponseEntity.ok(new GerRegResDTO(gerente.getId(), gerente.getCpf(),
        // gerente.getLogin(), gerente.getNome(), gerente.getTelefone()));
        return ResponseEntity.ok().build();

    }

    // R21 - Cadastrar ADMIN
    @Override
    public ResponseEntity<Void> insertAdmin(AdminRegistrationDTO data) {

        if (this.usuarioRepository.existsByLogin(data.email())) {
            // return ResponseEntity.badRequest().body("Administrador já cadastrado!");
            return ResponseEntity.badRequest().build(); // remove o body por enquanto
        }

        Administrador administrador = new Administrador();

        String senha = geradorSenha.gerarSenhaAleatoria();

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

        emailServicePort.sendApproveEmail(administrador.getLogin(), subject, message);

        // return ResponseEntity.ok()
        // .body("Administrador cadastrado com sucesso! Veja seu email: " +
        // administrador.getLogin());
        return ResponseEntity.ok().build();
    }

}
