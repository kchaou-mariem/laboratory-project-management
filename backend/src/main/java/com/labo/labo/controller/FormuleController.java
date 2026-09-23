package com.labo.labo.controller;

import com.labo.labo.entity.Formule;
import com.labo.labo.service.FormuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/formules")
@CrossOrigin
public class FormuleController {

    @Autowired
    FormuleService formuleService;

    @PostMapping("/create")
    public Formule createFormule(@RequestBody Formule formule){
       return formuleService.createFormule(formule);
    }
    @GetMapping("")
    public List<Formule> getAllFormules(){
        return formuleService.getAllFormules();
    }
    @GetMapping("/{id}")
    public Formule getFormule(@PathVariable Long id ){
        return formuleService.getFormule(id);
    }
    @PutMapping("/update/{id}")
    public Formule updateFormule(@RequestBody Formule formule ,@PathVariable Long id ){
        return formuleService.updateFormule(id,formule);
    }
    @GetMapping("/projets/{id}/formules")
    public List<Formule> getAllFormulesProjet(@PathVariable Long id){
        return formuleService.getFormulesByProjet(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteFormule(@PathVariable Long id){
        formuleService.deleteFormule(id);
    }

    @PutMapping("/{id}/valider")
    public Formule validerFormule(@PathVariable Long id){
        return formuleService.validerFormule(id);
    }

    @PutMapping("/reviser/{id}")
    public Formule reviserFormule(@PathVariable Long id){return formuleService.reviserFormule(id);}
}
