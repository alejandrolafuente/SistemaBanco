package com.bankserver.adapters.inbound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankserver.application.commands.CriarClienteCommand;
import com.bankserver.application.commands.EnderecoValue;
import com.bankserver.application.usecases.ClienteService;
import com.bankserver.application.usecases.ClienteServicePort;
import com.bankserver.dto.request.ClienteRegistrationDTO;
import com.bankserver.dto.request.DepositoDTO;
import com.bankserver.dto.request.SaqueDTO;
import com.bankserver.dto.request.TransferDTO;
import com.bankserver.dto.response.ClienteResponseDTO;
import com.bankserver.dto.response.R03ResDTO;
import com.bankserver.seguranca.UserDetailsImpl;

@RestController
@RequestMapping("cliente")
@CrossOrigin
public class ClienteController {

    private final ClienteServicePort clienteServicePort;

    public ClienteController(ClienteServicePort clienteServicePort) {
        this.clienteServicePort = clienteServicePort;
    }

    // R01 - cadastro cliente
    @PostMapping("/register")
    public ResponseEntity<ClienteResponseDTO> register(@RequestBody ClienteRegistrationDTO request) {

        CriarClienteCommand command = new CriarClienteCommand(
                request.cpf(),
                request.email(),
                request.nome(),
                request.telefone(),
                request.salario(),
                EnderecoValue.fromDTO(request.endereco()));

        return null;
    }

    // R03
    @GetMapping("/saldo/{userId}")
    public ResponseEntity<R03ResDTO> buscaSaldo(@PathVariable Long userId) {

        return clienteService.buscaSaldo(userId);
    }

    // R05
    @PostMapping("/deposito")
    public ResponseEntity<?> deposito(@RequestBody DepositoDTO request) {
        // return clienteService.realizarDeposito(request);
        System.out.println("\nDEPOSITO CHEGANDO => " + request);
        return ResponseEntity.ok().build();
    }

    // R06
    @PostMapping("/saque")
    public ResponseEntity<?> saque(@RequestBody SaqueDTO dto) {
        return clienteService.realizarSaque(dto);
    }

    // R07
    @PostMapping("/transferencia")
    public ResponseEntity<?> transferir(@RequestBody TransferDTO dto,
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return clienteService.realizarTransferencia(dto, userDetailsImpl);
    }

}
