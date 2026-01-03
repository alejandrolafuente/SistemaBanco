package com.bankserver.application.usecases;

import com.bankserver.application.commands.CriarAdminCommand;
import com.bankserver.application.commands.CriarGerenteCommand;
import com.bankserver.application.domain.Administrador;
import com.bankserver.application.domain.Gerente;

public interface AdminServicePort {

    // R17
    Gerente criarGerente(CriarGerenteCommand command);

    // R21 - Cadastrar ADMIN
    Administrador criarAdmin(CriarAdminCommand command);

}
