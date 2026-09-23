package com.labo.labo.controller;

import com.labo.labo.dto.ProjetDTO;
import com.labo.labo.entity.Formule;
import com.labo.labo.entity.Projet;
import com.labo.labo.enumeration.Statut;
import com.labo.labo.service.ProjetService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

//@PreAuthorize("")
@RequestMapping("/projets")
@RestController
@CrossOrigin
public class ProjetController {
    //private static final String ENTITY_NAME = "projet"; //remplacer projet par Entity_name dans le code
    @Autowired
    ProjetService projetService;
    //private final Logger log = LoggerFactory.getLogger(ProjetService.class);

    @PostMapping("/create")
    public Projet createProjet(@RequestBody Projet projet){
        return projetService.createProjet(projet);
    }
    @GetMapping("")
    public List<Projet> getAllProjets(){
        return projetService.getAllProjets();
    }
    @GetMapping("/{id}")
    public Optional<Projet> getProjet(@PathVariable Long id ){
        return projetService.getProjet(id);
    }
    @PutMapping("/update/{id}")
    public Projet updateProjet(@RequestBody Projet projet ,@PathVariable Long id ){
        return projetService.updateProjet(id,projet);
    }
    @PutMapping("/update/{id}/statut")
    public Projet updateStatutProjet(@RequestBody Statut statut , @PathVariable Long id ){
        return projetService.updateStatutProjet(id,statut);
    }
    @GetMapping("{id}/formules")
    public List<Formule> getAllFormulesProjet(@PathVariable Long id){
        return projetService.getFormulesByProjet(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteProjet(@PathVariable Long id){
        projetService.deleteProjet(id);
    }

    @PutMapping("{id}/statut/change")
    public Projet updateStatus(@PathVariable Long id ){
        return projetService.updateStatus(id);

    }

//    @GetMapping("")
//    public List<Projet>getAllProjetsSearch(@RequestBody String search){
//        List<Projet>projets;
//        if (search!=null){
//        projets=projetService.findProjetByNomsStartingWith(search);}
//        else{
//            projets=projetService.getAllProjets();
//        }
//        return projets;
//    }

    @GetMapping("/search")
    public ResponseEntity<List<Projet>> getAllProjetsSearch(@RequestParam(value = "search", required = false) String search) {
        List<Projet> projets;
        if (search != null && !search.trim().isEmpty()) {
            projets = projetService.findProjetByNomsStartingWith(search.trim());
        } else {
            projets = projetService.getAllProjets();
        }
        return ResponseEntity.ok(projets);
    }

}
