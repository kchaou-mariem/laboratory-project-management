package com.labo.labo.controller;

import com.labo.labo.entity.RoleCompte;
import com.labo.labo.entity.Tache;
import com.labo.labo.service.CompteService;
import com.labo.labo.service.RoleCompteService;
import com.labo.labo.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roleComptes")
@CrossOrigin
public class RoleCompteController {
    @Autowired
    RoleCompteService roleCompteService;

    @PostMapping("/create")
    public RoleCompte createRoleCompte(@RequestBody RoleCompte roleCompte){
        return roleCompteService.createRoleCompte(roleCompte);
    }

    @GetMapping("")
    public List<RoleCompte> getAllRoleComptes(){

        return roleCompteService.getAllRoleComptes();
    }

    @GetMapping("/{id}")
    public Optional<RoleCompte> getRoleCompte(@PathVariable Long id ){

        return roleCompteService.getRoleCompte(id);
    }

    @PutMapping("/update/{id}")
    public RoleCompte updateRoleCompte(@RequestBody RoleCompte roleCompte ,@PathVariable Long id ){
        return roleCompteService.updateRoleCompte(id,roleCompte);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRoleCompte(@PathVariable Long id){

        roleCompteService.deleteRoleCompte(id);
    }

}
