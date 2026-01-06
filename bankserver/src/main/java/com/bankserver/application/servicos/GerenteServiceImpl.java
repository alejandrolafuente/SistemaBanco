package com.bankserver.application.servicos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bankserver.adapters.outbound.ports.ClienteRepository;
import com.bankserver.adapters.outbound.ports.ContaRepository;
import com.bankserver.adapters.outbound.ports.EmailServicePort;
import com.bankserver.adapters.outbound.ports.SaldoRepository;
import com.bankserver.application.usecases.GerenteService;
import com.bankserver.dto.response.R09ResDTO;
import com.bankserver.utils.GeradorSenha;
import com.bankserver.application.domain.Cliente;
import com.bankserver.application.domain.Conta;
import com.bankserver.application.domain.Saldo;
import com.bankserver.application.domain.enums.StatusUsuario;

@Service
public class GerenteServiceImpl implements GerenteService {

    private final ContaRepository contaRepository;

    private final SaldoRepository saldoRepository;

    private final ClienteRepository clienteRepository;

    private final EmailServicePort emailService;

    private final GeradorSenha geradorSenha;

    public GerenteServiceImpl(
            ContaRepository contaRepository,
            SaldoRepository saldoRepository,
            ClienteRepository clienteRepository,
            EmailServicePort emailService,
            GeradorSenha geradorSenha) {
        this.contaRepository = contaRepository;
        this.saldoRepository = saldoRepository;
        this.clienteRepository = clienteRepository;
        this.emailService = emailService;
        this.geradorSenha = geradorSenha;
    }

    // R09 - tela inicial gerente
    @Override
    public ResponseEntity<?> solicitacoesPendentes(Long gerenteId) {

        List<Conta> contasPendentes = contaRepository.findContasPendentesByGerenteId(gerenteId);

        List<R09ResDTO> solicitacoes = contasPendentes.stream()
                .map(conta -> new R09ResDTO(
                        conta.getId(),
                        conta.getCliente().getCpf(),
                        conta.getCliente().getNome(),
                        conta.getCliente().getSalario()

                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(solicitacoes);

    }

    // R10 - Aprovar Cliente
    @Override
    public ResponseEntity<Void> aprovarCliente(Long contaId) {

        // ** CUSTOMIZAR EXCEPTIONS!!
        // Conta conta = contaRepository.findById(contaId)
        // .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));

        // 1 obter conta
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        conta.aprovar();

        contaRepository.update(conta);

        // precisamos registrar primeiro saldo com valor zero no historico
        // lembrar de atualizar este saldo a cada 24h
        Saldo saldoInicial = new Saldo();

        saldoInicial.setData(LocalDateTime.now());
        saldoInicial.setValor(BigDecimal.ZERO);
        saldoInicial.setConta(conta);

        saldoInicial = saldoRepository.save(saldoInicial);

        Cliente cliente = saldoInicial.getConta().getCliente();

        // update no cliente

        cliente.setStatus(StatusUsuario.ATIVO);

        cliente = this.clienteRepository.update(cliente);

        // cria senha e envia por email ao cliente
        // estou aqui!

        String senha = geradorSenha.gerarSenhaAleatoria();

        cliente.setSenha(new BCryptPasswordEncoder().encode(senha));

        String subject = "BANTADS: CADASTRO DE CLIENTE APROVADO";

        String message = "Seu cadastro foi aprovado, sua senha é " + senha;

        emailService.sendApproveEmail(cliente.getLogin(), subject, message);

        // return ResponseEntity.ok(new R10ResDTO(conta.getId(), cliente.getCpf(),
        // cliente.getNome()));
        return ResponseEntity.ok().build();

    }

}
