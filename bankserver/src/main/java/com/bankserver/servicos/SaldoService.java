package com.bankserver.servicos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankserver.model.Conta;
import com.bankserver.model.Saldo;
import com.bankserver.repository.ContaRepository;
import com.bankserver.repository.SaldoRepository;

@Service
@EnableScheduling
public class SaldoService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private SaldoRepository saldoRep;

    // método agendado
    @Transactional
    @Scheduled(cron = "0 0 23 * * *") // Todo dia às 23:00
    public void registrarSaldoDiario() {
        List<Conta> contas = contaRepository.findAll();

        for (Conta conta : contas) {
            Saldo saldoDiario = new Saldo();
            saldoDiario.setData(LocalDateTime.now());
            saldoDiario.setValor(conta.getSaldo());
            saldoDiario.setConta(conta);
            saldoRep.save(saldoDiario);
        }
    }

    // método para consulta (R8.4 - Extrato)
    public List<Saldo> getHistoricoSaldos(Long contaId, LocalDate inicio, LocalDate fim) {
        return saldoRep.findByContaIdAndDataBetween(contaId, inicio, fim);
    }
}
