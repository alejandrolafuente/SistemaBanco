package com.bankserver.adapters.outbound.adapters;

import com.bankserver.adapters.outbound.entidades.JpaAdministradorEntidade;
import com.bankserver.adapters.outbound.ports.AdminRepository;
import com.bankserver.adapters.outbound.repository.JpaAdminRepository;
import com.bankserver.application.domain.Administrador;

public class AdminRepositoryImpl implements AdminRepository {

    private final JpaAdminRepository jpaAdminRepository;

    public AdminRepositoryImpl(JpaAdminRepository jpaAdminRepository) {
        this.jpaAdminRepository = jpaAdminRepository;
    }

    @Override
    public Administrador save(Administrador administrador) {
        JpaAdministradorEntidade jpaAdministradorEntidade = new JpaAdministradorEntidade(administrador);
        return null;
    }

}
