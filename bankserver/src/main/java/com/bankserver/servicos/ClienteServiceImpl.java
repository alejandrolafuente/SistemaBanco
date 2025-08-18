package com.bankserver.servicos;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        String senha = generateRamdomPassword();

        // 4. Cria o Cliente (herda de Usuario)
        Cliente cliente = new Cliente();
        cliente.setCpf(data.cpf());
        cliente.setLogin(data.email());
        cliente.setNome(data.nome());
        cliente.setTelefone(data.telefone());
        cliente.setSenha(new BCryptPasswordEncoder().encode(senha));
        cliente.setPerfil(TipoUsuario.CLIENTE);
        cliente.setStatus(StatusUsuario.ATIVO);
        cliente.setSalario(data.salario());
        cliente.setEndereco(endereco); // associa o endereço salvo

        System.out.println("SENHA NO CADASTRO: " + senha);

        Conta conta = Conta.builder()
                .numeroConta(gerarNumeroConta())
                .dataCriacao(LocalDate.now())
                .limite(0.0)
                .statusConta(StatusConta.PENDENTE)
                .cliente(cliente)
                .build();

        clienteRep.save(cliente);

        contaRep.save(conta);

        return ResponseEntity.ok().body("Cliente cadastrado com sucesso! Status da conta: PENDENTE");
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

    // Método auxiliar para gerar número de conta aleatório
    private String gerarNumeroConta() {
        Random random = new Random();
        return String.format("%08d", random.nextInt(100000000));
    }
}
