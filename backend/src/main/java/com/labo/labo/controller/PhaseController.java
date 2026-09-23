package com.labo.labo.controller;

import com.labo.labo.dto.DetailsPhaseDTO;
import com.labo.labo.entity.Ingredient;
import com.labo.labo.entity.Phase;
import com.labo.labo.enumeration.Statut;
import com.labo.labo.service.DetailsPhaseService;
import com.labo.labo.service.PhaseService;
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

@RequestMapping("/phases")
@RestController
@CrossOrigin//(origins = "http://localhost:5173")
public class PhaseController {
    @Autowired
    PhaseService phaseService;

    //private final Logger log = LoggerFactory.getLogger(DetailsPhaseService.class);

    @PostMapping("/create/{formuleId}")
    public Phase createPhase(@RequestBody Phase phase,@PathVariable Long formuleId){
        return phaseService.createPhase(phase,formuleId);
    }

    @PostMapping("/create/fabrication/{formuleId}")
    public Phase createPhaseFabrication(@RequestBody Phase phase,@PathVariable Long formuleId){
        return phaseService.createPhaseFabrication(phase,formuleId);
    }
    @PostMapping("/create/test/{formuleId}")
    public Phase createPhaseTest(@RequestBody Phase phase,@PathVariable Long formuleId){
        return phaseService.createPhaseTest(phase,formuleId);
    }

    @PutMapping("/terminer/fabrication/{id}")
    public Phase finishPhaseFabrication(@PathVariable Long id){
       return phaseService.completePhaseFabrication(id);
    }


    @GetMapping("")
    public List<Phase> getAllPhase(){

        return phaseService.getAllPhases();
    }

    @GetMapping("/{id}")
    public Optional<Phase> getPhase(@PathVariable Long id ){

        return phaseService.getPhase(id);
    }

   // @CrossOrigin(origins = "http://localhost:5173/updatePhase")
    @PutMapping("/update/{id}")
    public Phase updatePhase(@RequestBody Phase phase ,@PathVariable Long id ){
        return phaseService.updatePhase(id,phase);
    }

    @PutMapping("/update/statut/{id}/{statut}")
    public Phase updateStatutPhase(@PathVariable Long id,@PathVariable String statut ){
        return phaseService.updateStatutPhase(id,statut);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePhase(@PathVariable Long id){

        phaseService.deletePhase(id);
    }
    @PutMapping("/{id}/description")
public  Phase updateDescription(@PathVariable Long id,@RequestBody  String description) {
        return phaseService.updatePhaseDescription(id,description);
    }
}
