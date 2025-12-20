package com.bankserver.application.servicos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bankserver.adapters.outbound.ports.ClienteRepository;
import com.bankserver.adapters.outbound.ports.ContaRepository;
import com.bankserver.adapters.outbound.ports.EnderecoRepository;
import com.bankserver.adapters.outbound.ports.GerenteRepository;
import com.bankserver.adapters.outbound.ports.UsuarioRepository;
import com.bankserver.adapters.outbound.repository.JpaSaldoRepository;
import com.bankserver.adapters.outbound.repository.JpaTransacaoRepository;
import com.bankserver.application.domain.Usuario;
import com.bankserver.application.domain.enums.StatusConta;
import com.bankserver.application.domain.enums.StatusUsuario;
import com.bankserver.application.domain.enums.TipoUsuario;
import com.bankserver.application.usecases.ClienteService;
import com.bankserver.dto.request.ClienteRegistrationDTO;
import com.bankserver.dto.request.DepositoDTO;
import com.bankserver.dto.request.SaqueDTO;
import com.bankserver.dto.request.TransferDTO;
import com.bankserver.dto.response.R03ResDTO;
import com.bankserver.exceptions.ClientNotFoundException;
import com.bankserver.application.domain.Cliente;
import com.bankserver.application.domain.Conta;
import com.bankserver.application.domain.Endereco;
import com.bankserver.application.domain.Gerente;
import com.bankserver.seguranca.UserDetailsImpl;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final UsuarioRepository usuarioRepository;

    private final GerenteRepository gerenteRepository;

    private final EnderecoRepository enderecoRepository;

    private final ClienteRepository clienteRepository;

    private final ContaRepository contaRepository;

    @Autowired
    private JpaTransacaoRepository transacaoRepository;

    @Autowired
    private JpaSaldoRepository saldoRepository;

    public ClienteServiceImpl(GerenteRepository gerenteRepository,
            UsuarioRepository usuarioRepository, EnderecoRepository enderecoRepository,
            ClienteRepository clienteRepository, ContaRepository contaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.gerenteRepository = gerenteRepository;
        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
        this.contaRepository = contaRepository;
    }

    // R01
    @Override
    public ResponseEntity<Void> insertClient(ClienteRegistrationDTO data) {

        if (this.usuarioRepository.existsByLogin(data.email())) {
            // return ResponseEntity.badRequest().body("Cliente já cadastrado");
            return ResponseEntity.badRequest().build();
        }

        Endereco endereco = new Endereco();

        endereco.setCep(data.endereco().cep());
        endereco.setUf(data.endereco().uf());
        endereco.setCidade(data.endereco().cidade());
        endereco.setBairro(data.endereco().bairro());
        endereco.setRua(data.endereco().rua());
        endereco.setNumero(data.endereco().numero());
        endereco.setComplemento(data.endereco().complemento());

        enderecoRepository.save(endereco);

        Cliente cliente = new Cliente();
        cliente.setCpf(data.cpf());
        cliente.setLogin(data.email());
        cliente.setNome(data.nome());
        cliente.setTelefone(data.telefone());
        cliente.setPerfil(TipoUsuario.CLIENTE);
        cliente.setStatus(StatusUsuario.PENDENTE);
        cliente.setSalario(data.salario());
        cliente.setEndereco(endereco); // associa o endereço salvo

        Conta conta = new Conta();
        conta.setNumeroConta(gerarNumeroConta());
        conta.setDataCriacao(LocalDateTime.now());
        conta.setLimite(calcularLimite(data.salario()));
        conta.setStatusConta(StatusConta.PENDENTE);
        conta.setCliente(cliente);
        conta.setGerente(gerenteRepository.findAllOrderByQuantidadeContas());

        clienteRepository.save(cliente);
        contaRepository.save(conta);

        // return ResponseEntity.ok().body("Cliente cadastrado com sucesso! Status da
        // conta: PENDENTE");
        return ResponseEntity.ok().build();
    }

    // // R03
    // @Override
    // public ResponseEntity<R03ResDTO> buscaSaldo(Long userId) {

    // Cliente cliente = clienteRep.findById(userId)
    // .orElseThrow(() -> new ClientNotFoundException("Cliente não encontrado para o
    // id = " + userId));

    // BigDecimal saldo = cliente.getConta().getSaldo();
    // BigDecimal limite = cliente.getConta().getLimite();

    // R03ResDTO response = new R03ResDTO(saldo, limite);

    // return ResponseEntity.ok(response);
    // }

    // // R05
    // @Override
    // @Transactional
    // public ResponseEntity<?> realizarDeposito(DepositoDTO dto) {

    // Cliente cliente = clienteRep.findById(dto.id())
    // .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    // Conta conta = cliente.getConta();

    // conta.depositar(dto.valor());

    // // Registra histórico
    // Saldo historicoSaldo = new Saldo();
    // historicoSaldo.setData(LocalDateTime.now());
    // historicoSaldo.setValor(conta.getSaldo());
    // historicoSaldo.setConta(conta);

    // contaRepository.save(conta);

    // return ResponseEntity.ok().build();
    // }

    // // R06
    // @Override
    // public ResponseEntity<?> realizarSaque(SaqueDTO dto) {

    // Cliente cliente = clienteRep.findById(dto.id())
    // .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    // Conta conta = cliente.getConta();

    // conta.retirar(dto.valor());

    // // Registra histórico
    // Saldo historicoSaldo = new Saldo();
    // historicoSaldo.setData(LocalDateTime.now());
    // historicoSaldo.setValor(conta.getSaldo());
    // historicoSaldo.setConta(conta);

    // contaRepository.save(conta);

    // return ResponseEntity.ok().build();
    // }

    // // R07
    // @Override
    // public ResponseEntity<?> realizarTransferencia(TransferDTO dto,
    // UserDetailsImpl userDetailsImpl) {

    // // extrai o id do cliente
    // Usuario usuarioLogado = userDetailsImpl.getUsuario();
    // Long userId = usuarioLogado.getId();

    // // busca o cliente e sua conta (conta de origem)
    // Cliente cliente = clienteRep.findById(userId)
    // .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    // Conta contaOrigem = cliente.getConta();

    // // busca conta destino
    // Conta contaDestino = contaRepository.findByNumeroConta(dto.contaDestino())
    // .orElseThrow(() -> new RuntimeException("Conta destino não encontrada"));

    // // executa a transferência
    // contaOrigem.transferir(dto.valor(), contaDestino);

    // // registra a transação
    // Transacao transacao = new Transacao();
    // transacao.setDataHora(LocalDateTime.now());
    // transacao.setValor(dto.valor());
    // transacao.setContaDestino(dto.contaDestino());
    // transacao.setTipo(TipoTransacao.TRANSFERENCIA);
    // transacao.setConta(contaOrigem);
    // transacaoRepository.save(transacao);

    // // registra saldo da conta origem
    // Saldo saldoOrigem = new Saldo();
    // saldoOrigem.setData(LocalDateTime.now());
    // saldoOrigem.setValor(contaOrigem.getSaldo());
    // saldoOrigem.setConta(contaOrigem);
    // saldoRepository.save(saldoOrigem);

    // // registra saldo da conta destino
    // Saldo saldoDestino = new Saldo();
    // saldoDestino.setData(LocalDateTime.now());
    // saldoDestino.setValor(contaDestino.getSaldo());
    // saldoDestino.setConta(contaDestino);
    // saldoRepository.save(saldoDestino);

    // // atualiza as contas
    // contaRepository.save(contaOrigem);
    // contaRepository.save(contaDestino);

    // return ResponseEntity.ok().build();
    // }

    private BigDecimal calcularLimite(BigDecimal salario) {
        if (salario.compareTo(new BigDecimal("2000.00")) >= 0) {
            return salario.divide(new BigDecimal("2"), 2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }

    // metodo auxiliar para gerar numero de conta aleatorio
    private String gerarNumeroConta() {
        Random random = new Random();
        return String.format("%08d", random.nextInt(100000000));
    }

    @Override
    public ResponseEntity<R03ResDTO> buscaSaldo(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscaSaldo'");
    }

    @Override
    public ResponseEntity<?> realizarDeposito(DepositoDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'realizarDeposito'");
    }

    @Override
    public ResponseEntity<?> realizarSaque(SaqueDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'realizarSaque'");
    }

    @Override
    public ResponseEntity<?> realizarTransferencia(TransferDTO dto, UserDetailsImpl userDetailsImpl) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'realizarTransferencia'");
    }

}
