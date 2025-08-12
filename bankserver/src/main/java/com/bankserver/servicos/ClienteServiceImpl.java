package com.bankserver.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankserver.dto.request.ClienteRegistrationDTO;
import com.bankserver.repository.ClienteRep;
import com.bankserver.repository.ContaRep;
import com.bankserver.repository.EnderecoRep;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRep clienteRep;

    @Autowired
    private EnderecoRep enderecoRep;

    @Autowired
    private ContaRep contaRep;

    @Override
    public Integer insertClient(ClienteRegistrationDTO data) {

        System.out.println("AFROCIBERDELIA ==>  " + data);

        return 1;
    }

}
