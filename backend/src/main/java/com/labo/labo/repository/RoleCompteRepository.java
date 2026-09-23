package com.labo.labo.repository;

import com.labo.labo.entity.CleRoleCompte;
import com.labo.labo.entity.RoleCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RoleCompteRepository extends JpaRepository<RoleCompte, Long> {
}
