package com.bankserver.servicos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankserver.dto.request.ClienteRegistrationDTO;
import com.bankserver.dto.request.DepositoDTO;
import com.bankserver.model.Cliente;
import com.bankserver.model.Conta;
import com.bankserver.model.Endereco;
import com.bankserver.model.Gerente;
import com.bankserver.model.Saldo;
import com.bankserver.model.StatusConta;
import com.bankserver.model.StatusUsuario;
import com.bankserver.model.TipoUsuario;
import com.bankserver.repository.ClienteRep;
import com.bankserver.repository.ContaRep;
import com.bankserver.repository.EnderecoRep;
import com.bankserver.repository.GerenteRep;
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

    @Autowired
    private GerenteRep gerenteRep;

    // R01
    @Override
    @Transactional
    public ResponseEntity<?> insertClient(ClienteRegistrationDTO data) {

        if (this.usuarioRep.existsByLogin(data.email())) {
            return ResponseEntity.badRequest().body("Cliente já cadastrado");
        }

        Endereco endereco = new Endereco();
        endereco.setCep(data.endereco().cep());
        endereco.setUf(data.endereco().uf());
        endereco.setCidade(data.endereco().cidade());
        endereco.setBairro(data.endereco().bairro());
        endereco.setRua(data.endereco().rua()); // Atenção: aqui está usando bairro() para rua - é isso mesmo?
        endereco.setNumero(data.endereco().numero());
        endereco.setComplemento(data.endereco().complemento());

        enderecoRep.save(endereco);

        Cliente cliente = new Cliente();
        cliente.setCpf(data.cpf());
        cliente.setLogin(data.email());
        cliente.setNome(data.nome());
        cliente.setTelefone(data.telefone());
        cliente.setPerfil(TipoUsuario.CLIENTE);
        cliente.setStatus(StatusUsuario.PENDENTE);
        cliente.setSalario(data.salario());
        cliente.setEndereco(endereco); // associa o endereço salvo

        Conta conta = Conta.builder()
                .numeroConta(gerarNumeroConta())
                .dataCriacao(LocalDateTime.now())
                .limite(calcularLimite(data.salario()))
                .statusConta(StatusConta.PENDENTE)
                .cliente(cliente)
                .gerente(encontrarGerenteComMenosContas())
                .build();

        clienteRep.save(cliente);

        contaRep.save(conta);

        return ResponseEntity.ok().body("Cliente cadastrado com sucesso! Status da conta: PENDENTE");
    }

    // R03
    @Override
    public ResponseEntity<?> buscaSaldo(Long userId) {

        Cliente cliente = clienteRep.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Double saldo = cliente.getConta().getSaldo();

        return ResponseEntity.ok(saldo);
    }

    // R05
    @Override
    @Transactional
    public ResponseEntity<?> realizarDeposito(DepositoDTO dto) {

        Cliente cliente = clienteRep.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Conta conta = cliente.getConta();
        conta.depositar(dto.valor());

        // Registra histórico
        Saldo historicoSaldo = new Saldo();
        historicoSaldo.setData(LocalDateTime.now());
        historicoSaldo.setValor(conta.getSaldo());
        historicoSaldo.setConta(conta);

        contaRep.save(conta);

        return ResponseEntity.ok().build();
    }

    private Gerente encontrarGerenteComMenosContas() {

        Pageable limit = PageRequest.of(0, 1);
        List<Gerente> gerentes = gerenteRep.findAllOrderByQuantidadeContas(limit);

        if (gerentes.isEmpty()) {
            throw new IllegalStateException("Não há gerentes cadastrados no sistema");
        }

        return gerentes.get(0); // SEMPRE retorna um gerente, mesmo que seja o único
    }

    private Double calcularLimite(Double salario) {
        if (salario >= 2000.0) {
            return salario / 2;
        }
        return 0.0;
    }

    // Método auxiliar para gerar número de conta aleatório
    private String gerarNumeroConta() {
        Random random = new Random();
        return String.format("%08d", random.nextInt(100000000));
    }

}
