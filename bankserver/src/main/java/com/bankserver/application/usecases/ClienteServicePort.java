package com.bankserver.application.usecases;

import org.springframework.http.ResponseEntity;

import com.bankserver.dto.request.ClienteRegistrationDTO;
import com.bankserver.dto.request.DepositoDTO;
import com.bankserver.dto.request.SaqueDTO;
import com.bankserver.dto.request.TransferDTO;
import com.bankserver.dto.response.R03ResDTO;
import com.bankserver.seguranca.UserDetailsImpl;

public interface ClienteServicePort {

    // R01 - cadastro cliente
    ResponseEntity<Void> insertClient(ClienteRegistrationDTO data);

    // R03
    ResponseEntity<R03ResDTO> buscaSaldo(Long userId);

    // R05
    ResponseEntity<?> realizarDeposito(DepositoDTO dto);

    // R06
    ResponseEntity<?> realizarSaque(SaqueDTO dto);

    // R07
    ResponseEntity<?> realizarTransferencia(TransferDTO dto,
            UserDetailsImpl userDetailsImpl);
}
