package com.labo.labo.controller;

import com.labo.labo.dto.CompteDTO;
import com.labo.labo.entity.Compte;
import com.labo.labo.entity.DetailsPhase;
import com.labo.labo.service.CompteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.labo.labo.entity.QCompte.compte;

@RequestMapping("/comptes")
@RestController
@CrossOrigin
public class CompteController {
   // private static final String ENTITY_NAME = "compte"; //remplacer compte par Entity_name dans le code
    @Autowired
    CompteService compteService;

    @PostMapping("/create")
    public Compte createCompte(@RequestBody Compte compte){
        return compteService.createCompte(compte);
    }

    @GetMapping("")
    public List<Compte> getAllompte(){

        return compteService.getAllComptes();
    }

    @GetMapping("/id/{id}")
    public Optional<Compte> getCompte(@PathVariable Long id ){
        return compteService.getCompte(id);
    }

    @GetMapping("/email/{email}")
    public Optional<Compte> getCompteByEmail(@PathVariable String email ){

        return compteService.getCompteByEmail(email);
    }

    @PutMapping("/update/{id}")
    public Compte updateCompte(@RequestBody Compte compte ,@PathVariable Long id ){
        return compteService.updateCompte(id,compte);
    }



    @DeleteMapping("/delete/{id}")
    public void deleteCompte(@PathVariable Long id){

        compteService.deleteCompte(id);
    }

//    @PostMapping("/verifierConnexion")
//    public Compte verifierConnexion(@RequestBody Map<String,String> cpt){
//       Compte compte=verifierConnexion(cpt);
//        if(compte!=null){
//            return compte;
//        }
//        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Echec de mot de passe: Email ou mot de passe incorrect");
//    }
@PostMapping("/verifierConnexion")
public ResponseEntity<?> verifierConnexion(@RequestBody Map<String, String> cpt) {
    Optional<Compte> resultat = compteService.verifConnexion(cpt);
    return resultat
            .map(ResponseEntity::ok)  // Si présent, retourne le compte avec le statut 200 OK
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Echec de connexion: Email ou mot de passe incorrect"));
}


    @GetMapping("/chefs")
    public List<Compte> getListChefProjet(){
        return compteService.ListChefProjet();
    }

}
