package com.labo.labo.controller;

import com.labo.labo.entity.DetailsPhase;
import com.labo.labo.entity.Phase;
import com.labo.labo.service.DetailsPhaseService;
import com.labo.labo.service.PhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detailsPhases")
@CrossOrigin
public class DetailsPhaseController {

    @Autowired
    DetailsPhaseService detailsPhaseService;



    //private final Logger log = LoggerFactory.getLogger(DetailsPhaseService.class);

    @PostMapping("/create")
    public DetailsPhase createDetailsPhase(@RequestBody DetailsPhase detailsPhase){
        return detailsPhaseService.createDetailsPhase(detailsPhase);
    }

    @GetMapping("")
    public List<DetailsPhase> getAllDetailsPhase(){

        return detailsPhaseService.getAllDetailsPhases();
    }

    @GetMapping("/{id}")
    public Optional<DetailsPhase> getDetailsPhase(@PathVariable Long id ){

        return detailsPhaseService.getDetailsPhase(id);
    }

    @PutMapping("/update/{id}")
    public DetailsPhase updateDetailsPhase(@RequestBody DetailsPhase detailsPhase ,@PathVariable Long id ){
        return detailsPhaseService.updateDetailsPhase(id,detailsPhase);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDetailsPhase(@PathVariable Long id){

        detailsPhaseService.deleteDetailsPhase(id);
    }




}
