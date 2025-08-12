package com.bankserver.servicos;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankserver.dto.request.ClienteRegistrationDTO;
import com.bankserver.model.Cliente;
import com.bankserver.model.Conta;
import com.bankserver.model.Endereco;
import com.bankserver.model.StatusConta;
import com.bankserver.model.StatusUsuario;
import com.bankserver.model.TipoUsuario;
import com.bankserver.repository.ClienteRep;
import com.bankserver.repository.ContaRep;
import com.bankserver.repository.EnderecoRep;
import com.bankserver.repository.UsuarioRep;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private UsuarioRep usuarioRep;

    @Autowired
    private ClienteRep clienteRep;

    @Autowired
    private EnderecoRep enderecoRep;

    @Autowired
    private ContaRep contaRep;

    @Override
    public ResponseEntity<?> insertClient(ClienteRegistrationDTO data) {

        if (this.usuarioRep.findByLogin(data.email()) != null)
            return ResponseEntity.badRequest().build();

        Endereco endereco = Endereco.builder()
                .cep(data.endereco().cep())
                .uf(data.endereco().uf())
                .cidade(data.endereco().cidade())
                .bairro(data.endereco().bairro())
                .rua(data.endereco().bairro())
                .numero(data.endereco().numero())
                .complemento(data.endereco().complemento())
                .build();

        enderecoRep.save(endereco);

        Conta conta = Conta.builder()
                .numeroConta(gerarNumeroConta())
                .dataCriacao(LocalDate.now())
                .limite(0.0)
                .statusConta(StatusConta.PENDENTE)
                .build();

        // 4. Cria o Cliente (herda de Usuario)
        Cliente cliente = new Cliente();
        cliente.setCpf(data.cpf());
        cliente.setLogin(data.email());
        cliente.setNome(data.nome());
        cliente.setTelefone(data.telefone());
        cliente.setSenha("");
        cliente.setPerfil(TipoUsuario.CLIENTE);
        cliente.setStatus(StatusUsuario.PENDENTE);
        cliente.setSalario(data.salario());
        cliente.setEndereco(endereco); // associa o endereço salvo
        cliente.setConta(conta); // associa a conta

        clienteRep.save(cliente);

        return ResponseEntity.ok().body("Cliente cadastrado com sucesso! Status da conta: PENDENTE");
    }

    // Método auxiliar para gerar número de conta aleatório
    private String gerarNumeroConta() {
        Random random = new Random();
        return String.format("%08d", random.nextInt(100000000));
    }
}
