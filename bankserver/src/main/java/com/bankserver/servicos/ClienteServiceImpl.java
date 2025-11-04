package com.bankserver.servicos;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import com.bankserver.dto.request.SaqueDTO;
import com.bankserver.dto.request.TransferDTO;
import com.bankserver.model.Cliente;
import com.bankserver.model.Conta;
import com.bankserver.model.Endereco;
import com.bankserver.model.Gerente;
import com.bankserver.model.Saldo;
import com.bankserver.model.StatusConta;
import com.bankserver.model.StatusUsuario;
import com.bankserver.model.TipoTransacao;
import com.bankserver.model.TipoUsuario;
import com.bankserver.model.Transacao;
import com.bankserver.model.Usuario;
import com.bankserver.repository.ClienteRep;
import com.bankserver.repository.ContaRepository;
import com.bankserver.repository.EnderecoRep;
import com.bankserver.repository.GerenteRep;
import com.bankserver.repository.SaldoRepository;
import com.bankserver.repository.TransacaoRepository;
import com.bankserver.repository.UsuarioRep;
import com.bankserver.seguranca.UserDetailsImpl;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private UsuarioRep usuarioRep;

    @Autowired
    private ClienteRep clienteRep;

    @Autowired
    private EnderecoRep enderecoRep;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private GerenteRep gerenteRep;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private SaldoRepository saldoRepository;

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

        contaRepository.save(conta);

        return ResponseEntity.ok().body("Cliente cadastrado com sucesso! Status da conta: PENDENTE");
    }

    // R03
    @Override
    public ResponseEntity<?> buscaSaldo(Long userId) {

        Cliente cliente = clienteRep.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        BigDecimal saldo = cliente.getConta().getSaldo();

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

        contaRepository.save(conta);

        return ResponseEntity.ok().build();
    }

    // R06
    @Override
    public ResponseEntity<?> realizarSaque(SaqueDTO dto) {

        Cliente cliente = clienteRep.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Conta conta = cliente.getConta();

        conta.retirar(dto.valor());

        // Registra histórico
        Saldo historicoSaldo = new Saldo();
        historicoSaldo.setData(LocalDateTime.now());
        historicoSaldo.setValor(conta.getSaldo());
        historicoSaldo.setConta(conta);

        contaRepository.save(conta);

        return ResponseEntity.ok().build();
    }

    // R07
    @Override
    public ResponseEntity<?> realizarTransferencia(TransferDTO dto, UserDetailsImpl userDetailsImpl) {

        // extrai o id do cliente
        Usuario usuarioLogado = userDetailsImpl.getUsuario();
        Long userId = usuarioLogado.getId();

        // busca o cliente e sua conta (conta de origem)
        Cliente cliente = clienteRep.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Conta contaOrigem = cliente.getConta();

        // busca conta destino
        Conta contaDestino = contaRepository.findByNumeroConta(dto.contaDestino())
                .orElseThrow(() -> new RuntimeException("Conta destino não encontrada"));

        // executa a transferência
        contaOrigem.transferir(dto.valor(), contaDestino);

        // registra a transação
        Transacao transacao = new Transacao();
        transacao.setDataHora(LocalDateTime.now());
        transacao.setValor(dto.valor());
        transacao.setContaDestino(dto.contaDestino());
        transacao.setTipo(TipoTransacao.TRANSFERENCIA);
        transacao.setConta(contaOrigem);
        transacaoRepository.save(transacao);

        // registra saldo da conta origem
        Saldo saldoOrigem = new Saldo();
        saldoOrigem.setData(LocalDateTime.now());
        saldoOrigem.setValor(contaOrigem.getSaldo());
        saldoOrigem.setConta(contaOrigem);
        saldoRepository.save(saldoOrigem);

        // registra saldo da conta destino
        Saldo saldoDestino = new Saldo();
        saldoDestino.setData(LocalDateTime.now());
        saldoDestino.setValor(contaDestino.getSaldo());
        saldoDestino.setConta(contaDestino);
        saldoRepository.save(saldoDestino);

        // atualiza as contas
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

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

    private BigDecimal calcularLimite(BigDecimal salario) {
        if (salario.compareTo(new BigDecimal("2000.00")) >= 0) {
            return salario.divide(new BigDecimal("2"), 2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }

    // Método auxiliar para gerar número de conta aleatório
    private String gerarNumeroConta() {
        Random random = new Random();
        return String.format("%08d", random.nextInt(100000000));
    }

}
