package com.AirBnb.Controller;

import com.AirBnb.Model.Roles;
import com.AirBnb.Repository.RolesRepository;
import com.AirBnb.Service.RolesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

    private final RolesService rolesService;
    private final RolesRepository rolesRepository;


    public RolesController(RolesService rolesService,
                           RolesRepository rolesRepository) {
        this.rolesService = rolesService;
        this.rolesRepository = rolesRepository;
    }

    //URL:http://localhost:8080/api/roles/addRoles

    @PostMapping("/addRoles")
    public ResponseEntity<?> addRoles(@RequestBody Roles roles) {

        System.out.println("Received Role Name: " + roles.getRoleName());
        Roles role =rolesService.addRoles(roles);

        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    //URL:http://localhost:8080/api/roles/deleteRoles

    @DeleteMapping("/deleteRoles")
    public ResponseEntity<?> deleteRoles(@RequestParam Long roleId){
        rolesService.deleteRoles(roleId);
        return new ResponseEntity<>(roleId+ "is Deleted From Record",HttpStatus.OK);
    }

    //URL:http://localhost:8080/api/roles/getAllRoles

    @GetMapping("/getAllRoles")
    public ResponseEntity<List<Roles>>  getAllRoles(){
        List<Roles> roles = rolesService.getAllRoles();
        return new ResponseEntity<>(roles,HttpStatus.OK);
    }

    //URL:http://localhost:8080/api/roles/updateRoles

    @PutMapping("/updateRoles")
    public ResponseEntity<Roles> updateRoles(@RequestBody Roles roles,@RequestParam Long roleId){
        Roles rol =rolesService.updateRoles(roles, roleId);
        return new ResponseEntity<>(rol,HttpStatus.OK);
    }

}
