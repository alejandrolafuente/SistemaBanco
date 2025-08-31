package com.bankserver.servicos;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankserver.dto.response.R09ResDTO;
import com.bankserver.dto.response.R10ResDTO;
import com.bankserver.model.Cliente;
import com.bankserver.model.Conta;
import com.bankserver.model.StatusUsuario;
import com.bankserver.repository.ContaRep;
import com.bankserver.utils.ServicoEmail;

@Service
public class GerenteServiceImpl implements GerenteService {

    @Autowired
    private ContaRep contaRep;

    @Autowired
    private ServicoEmail servicoEmail;

    // R09
    @Override
    public ResponseEntity<?> solicitacoesPendentes(Long id) {

        List<Conta> contasPendentes = contaRep.findContasPendentesByGerenteId(id);

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

    // R10
    @Override
    @Transactional
    public ResponseEntity<?> aprovarCliente(Long contaId) {

        // ** CUSTOMIZAR EXCEPTIONS!!
        // Conta conta = contaRep.findById(contaId)
        // .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));

        Conta conta = contaRep.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        conta.aprovar();

        Cliente cliente = conta.getCliente();
        cliente.setStatus(StatusUsuario.ATIVO);

        String senha = generateRamdomPassword();
        cliente.setSenha(new BCryptPasswordEncoder().encode(senha));

        String subject = "BANTADS: CADASTRO APROVADO";

        String message = "Seu cadastro foi aprovado, sua senha é " + senha;

        servicoEmail.sendApproveEmail(cliente.getLogin(), subject, message);

        return ResponseEntity.ok(new R10ResDTO(conta.getId(), cliente.getCpf(), cliente.getNome()));

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
