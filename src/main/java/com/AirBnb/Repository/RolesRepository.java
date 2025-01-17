package com.AirBnb.Repository;

import com.AirBnb.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByRoleName(String roleName);

}