package com.bankserver.servicos;

import org.springframework.http.ResponseEntity;

import com.bankserver.dto.request.ClienteRegistrationDTO;
import com.bankserver.dto.request.DepositoDTO;
import com.bankserver.dto.request.SaqueDTO;
import com.bankserver.dto.request.TransferDTO;
import com.bankserver.seguranca.UserDetailsImpl;

public interface ClienteService {

    // R01
    ResponseEntity<?> insertClient(ClienteRegistrationDTO data);

    // R03
    ResponseEntity<?> buscaSaldo(Long userId);

    // R05
    ResponseEntity<?> realizarDeposito(DepositoDTO dto);

    // R06
    ResponseEntity<?> realizarSaque(SaqueDTO dto);

    // R07
    ResponseEntity<?> realizarTransferencia(TransferDTO dto,
            UserDetailsImpl userDetailsImpl);
}
