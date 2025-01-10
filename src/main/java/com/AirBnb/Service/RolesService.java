package com.AirBnb.Service;

import com.AirBnb.Model.Roles;
import com.AirBnb.Repository.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    private final RolesRepository rolesRepository;

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }


    public Roles addRoles(Roles roles) {
        Roles roleVal = rolesRepository.save(roles);
        return roleVal;
    }

    public void deleteRoles(Long roleId) {
        rolesRepository.deleteById(roleId);
    }

    public List<Roles> getAllRoles() {
        return rolesRepository.findAll();
    }

    public Roles updateRoles(Roles roles, Long roleId) {
        Roles role = rolesRepository.findById(roleId).get();

        role.setRoleName(roles.getRoleName());
        Roles save = rolesRepository.save(role);
        return save;
    }


}
