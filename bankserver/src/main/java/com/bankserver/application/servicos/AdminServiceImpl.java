package com.bankserver.application.servicos;

import org.springframework.stereotype.Service;

import com.bankserver.adapters.outbound.ports.AdminRepository;
import com.bankserver.adapters.outbound.ports.EmailServicePort;
import com.bankserver.adapters.outbound.ports.GerenteRepository;
import com.bankserver.adapters.outbound.ports.HashSenhaPort;
import com.bankserver.adapters.outbound.ports.UsuarioRepository;
import com.bankserver.application.commands.CriarAdminCommand;
import com.bankserver.application.commands.CriarGerenteCommand;
import com.bankserver.application.domain.Administrador;
import com.bankserver.application.usecases.AdminServicePort;
import com.bankserver.utils.GeradorSenha;
import com.bankserver.application.domain.Gerente;
import com.bankserver.application.domain.enums.StatusUsuario;
import com.bankserver.application.domain.enums.TipoUsuario;
import com.bankserver.application.domain.exceptions.UsuarioJaCadastradoException;

@Service
public class AdminServiceImpl implements AdminServicePort {

    private final UsuarioRepository usuarioRepository;

    private final AdminRepository adminRepository;

    private final GerenteRepository gerenteRepository;

    private final EmailServicePort emailServicePort;

    private final GeradorSenha geradorSenha;

    private final HashSenhaPort hashSenhaPort;

    // injecao via construtor - seguindo Hexagonal
    public AdminServiceImpl(UsuarioRepository usuarioRepository,
            AdminRepository adminRepository,
            GerenteRepository gerenteRepository,
            EmailServicePort emailServicePort,
            GeradorSenha geradorSenha,
            HashSenhaPort hashSenhaPort) {
        this.usuarioRepository = usuarioRepository;
        this.adminRepository = adminRepository;
        this.gerenteRepository = gerenteRepository;
        this.emailServicePort = emailServicePort;
        this.geradorSenha = geradorSenha;
        this.hashSenhaPort = hashSenhaPort;
    }

    // R17 - cadastrar gerente
    @Override
    public Gerente criarGerente(CriarGerenteCommand command) {

        if (this.usuarioRepository.existsByLogin(command.getEmail())) {
            throw new UsuarioJaCadastradoException(command.getEmail(), "Administrador");
        }

        Gerente gerente = new Gerente();

        String senha = geradorSenha.gerarSenhaAleatoria();

        gerente.setCpf(command.getCpf());
        gerente.setLogin(command.getEmail());
        gerente.setNome(command.getNome());
        gerente.setTelefone(command.getTelefone());
        gerente.setSenha(hashSenhaPort.hash(senha));
        gerente.setPerfil(TipoUsuario.GERENTE);
        gerente.setStatus(StatusUsuario.ATIVO);

        Gerente gerenteSalvo = gerenteRepository.save(gerente);

        String subject = "BANTADS: CADASTRO DE GERENTE APROVADO";

        String message = "Seu cadastro como gerente foi aprovado, sua senha é " + senha;

        System.out.println("SENHA NO CADASTRO GERENTE: " + senha);

        emailServicePort.sendApproveEmail(gerente.getLogin(), subject, message);

        return gerenteSalvo;

    }

    // R21 - cadastrar admin
    @Override
    public Administrador criarAdmin(CriarAdminCommand command) {

        // excecao especifica
        if (this.usuarioRepository.existsByLogin(command.getEmail())) {
            throw new UsuarioJaCadastradoException(command.getEmail(), "Administrador");
        }

        Administrador administrador = new Administrador();

        String senha = geradorSenha.gerarSenhaAleatoria();

        administrador.setCpf(command.getCpf());
        administrador.setLogin(command.getEmail());
        administrador.setNome(command.getNome());
        administrador.setTelefone(command.getTelefone());
        administrador.setSenha(hashSenhaPort.hash(senha));
        administrador.setPerfil(TipoUsuario.ADMIN);
        administrador.setStatus(StatusUsuario.ATIVO);

        Administrador adminSalvo = adminRepository.save(administrador);

        String subject = "BANTADS: CADASTRO DE ADMINISTADOR APROVADO";

        String message = "Seu cadastro como administrador foi aprovado, sua senha é " + senha;

        System.out.println("SENHA NO CADASTRO ADMIN: " + senha);

        emailServicePort.sendApproveEmail(administrador.getLogin(), subject, message);

        return adminSalvo;
    }

}
