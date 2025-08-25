package com.bankserver.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankserver.dto.response.R09ResDTO;
import com.bankserver.model.Conta;
import com.bankserver.repository.ContaRep;

@Service
public class GerenteServiceImpl implements GerenteService {

    @Autowired
    private ContaRep contaRep;

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

}
