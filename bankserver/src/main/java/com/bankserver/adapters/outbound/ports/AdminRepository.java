package com.bankserver.adapters.outbound.ports;

import com.bankserver.application.domain.Administrador;

public interface AdminRepository {

    Administrador save(Administrador administrador);
    
}
