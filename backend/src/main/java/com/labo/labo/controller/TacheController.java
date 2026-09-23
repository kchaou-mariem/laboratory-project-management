package com.labo.labo.controller;
import com.labo.labo.dto.TacheDTO;
import com.labo.labo.entity.Phase;
import com.labo.labo.entity.Tache;
import com.labo.labo.service.PhaseService;
import com.labo.labo.service.TacheService;
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


@RestController
@RequestMapping("/taches")
@CrossOrigin
public class TacheController {

    @Autowired
    TacheService tacheService;
    //private final Logger log = LoggerFactory.getLogger(TacheService.class);

//    public TacheController(TacheService tacheService) {
//        this.tacheService = tacheService;
//    }


    //private final Logger log = LoggerFactory.getLogger(DetailsPhaseService.class);

    @PostMapping("/create")
    public Tache createTache(@RequestBody Tache tache){
        return tacheService.createTache(tache);
    }

    @GetMapping("")
    public List<Tache> getAllTaches(){

        return tacheService.getAllTaches();
    }

    @GetMapping("/{id}")
    public Optional<Tache> getTache(@PathVariable Long id ){

        return tacheService.getTache(id);
    }

    @PutMapping("/update/{id}")
    public Tache updateTache(@RequestBody Tache tache ,@PathVariable Long id ){
        return tacheService.updateTache(id,tache);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTache(@PathVariable Long id){

        tacheService.deleteTache(id);
    }


//    /**
//     * POST /taches : Create a new tache.
//     *
//     * @param tacheDTO
//     * @param bindingResult Résultat de la liaison des données de la requête avec l'objet Java
//     * @return the ResponseEntity with status 201 (Created) and with body the new qualification, or with status 400 (Bad Request) if the qualification has already an ID
//     * @throws URISyntaxException                      if the Location URI syntax is incorrect
//     * @throws org.springframework.web.bind.MethodArgumentNotValidException si l'objet avec @valid échoue à la validation
//     */
//    @PostMapping("/create")
//    public ResponseEntity<TacheDTO> createTache(@Valid @RequestBody TacheDTO tacheDTO, BindingResult bindingResult)
//            throws MethodArgumentNotValidException {
//        log.debug("REST request to save tache : {}", tacheDTO);
//       /* if (tacheDTO.getCodqual() != null || !qualificationDTO.getCodqual().isEmpty()) {
//            bindingResult.addError(new FieldError("QualificationDTO", "codqual", "POST method does not accept " + ENTITY_NAME + " with code"));
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }*/
//        if (bindingResult.hasErrors()) {
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }
//        /*Ainsi, en passant null comme premier paramètre, l'exception est utilisée pour signaler une erreur de validation globale
//        sur la demande ou la méthode, et non une erreur spécifique liée à un paramètre de méthode individuel.*/
//        TacheDTO result = tacheService.save(tacheDTO);
//        return new ResponseEntity<>(result, HttpStatus.CREATED);
//    }
//
//    /*
//     * PUT /qualifications : Updates an existing qualification.
//     *
//     * @param id
//     * @param qualificationDTO the qualification to update
//     * @return the ResponseEntity with status 200 (OK) and with body the updated qualification,
//     * or with status 400 (Bad Request) if the qualification is not valid,
//     * or with status 500 (Internal Server Error) if the qualification couldn't be updated
//     * @throws org.springframework.web.bind.MethodArgumentNotValidException
//     */
//    @PutMapping("/update/{id}")
//    public ResponseEntity<TacheDTO> updateTache(@PathVariable Long id, @Valid @RequestBody TacheDTO tacheDTO)
//            throws MethodArgumentNotValidException {
//        log.debug("Request to update tache: {}", id);
//        TacheDTO result = tacheService.update(tacheDTO);
//        return ResponseEntity.ok().body(result);
//    }
//
//    /**
//     * GET /qualifications/{id} : get the "id" qualification.
//     *
//     * @param id the id of the qualification to retrieve
//     * @return the ResponseEntity with status 200 (OK) and with body of qualification, or with status 404 (Not Found)
//     */
//    @GetMapping("/{id}")
//    public ResponseEntity<TacheDTO> getTache(@PathVariable Long id) {
//        log.debug("Request to get tache: {}", id);
//        TacheDTO dto = tacheService.getTacheById(id);
//        //RestPreconditions.checkFound(dto, "qualification.NotFound");
//        return ResponseEntity.ok().body(dto);
//    }
//
//    /**
//     * GET /qualifications : get all the qualifications.
//     *
//     * @return the ResponseEntity with status 200 (OK) and the list of qualifications in body
//     */
//    @GetMapping("/all")
//    public Collection<TacheDTO> getAllTaches() {
//        log.debug("Request to get all taches : {}");
//        return tacheService.getAll();
//    }
//
//    /**
//     * DELETE /qualifications/{id} : deleteById the "id" qualification.
//     *
//     * @param id the id of the qualification to deleteById
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteTache(@PathVariable Long id) {
//        log.debug("Request to deleteById tache: {}", id);
//        tacheService.delete(id);
//        return ResponseEntity.ok().build();
//    }
}
