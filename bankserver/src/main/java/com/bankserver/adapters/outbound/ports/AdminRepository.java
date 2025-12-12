package com.bankserver.adapters.outbound.ports;

import com.bankserver.model.Administrador;

public interface AdminRepository {

    Administrador save(Administrador administrador);
    
}
