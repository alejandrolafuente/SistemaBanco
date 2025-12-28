package com.bankserver.adapters.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankserver.adapters.outbound.entidades.JpaAdministradorEntidade;

@Repository
public interface JpaAdminRepository extends JpaRepository<JpaAdministradorEntidade, Long> {

}
