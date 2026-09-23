package com.labo.labo.controller;


import com.labo.labo.dto.SousFamilleDTO;
import com.labo.labo.entity.SousFamille;
import com.labo.labo.entity.Tache;
import com.labo.labo.service.SousFamilleService;
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
@RequestMapping("/sousFamilles")
@CrossOrigin
public class SousFamilleController {
    //private static final String ENTITY_NAME = "sousFamille"; //remplacer sousFamille par Entity_name dans le code

    @Autowired
    SousFamilleService sousFamilleService;

    @PostMapping("/create")
    public SousFamille createSousFamille(@RequestBody SousFamille sousFamille){
        return sousFamilleService.createSousFamille(sousFamille);
    }

    @GetMapping("")
    public List<SousFamille> getAllSousFamilles(){

        return sousFamilleService.getAllSousFamilles();
    }

    @GetMapping("/{id}")
    public Optional<SousFamille> getSousFamille(@PathVariable Long id ){

        return sousFamilleService.getSousFamille(id);
    }

    @PutMapping("/update/{id}")
    public SousFamille updateSousFamille(@RequestBody SousFamille sousFamille ,@PathVariable Long id ){
        return sousFamilleService.updateSousFamille(id,sousFamille);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSousFamille(@PathVariable Long id){

        sousFamilleService.deleteSousFamille(id);
    }

    //private final Logger log = LoggerFactory.getLogger(SousFamilleService.class);

//    public SousFamilleController(SousFamilleService sousFamilleService) {
//        this.sousFamilleService = sousFamilleService;
//    }
//
//    /**
//     * POST /sousFamilles : Create a new sousFamille.
//     *
//     * @param sousFamilleDTO
//     * @param bindingResult Résultat de la liaison des données de la requête avec l'objet Java
//     * @return the ResponseEntity with status 201 (Created) and with body the new qualification, or with status 400 (Bad Request) if the qualification has already an ID
//     * @throws URISyntaxException                      if the Location URI syntax is incorrect
//     * @throws org.springframework.web.bind.MethodArgumentNotValidException si l'objet avec @valid échoue à la validation
//     */
//    @PostMapping("/create")
//    public ResponseEntity<SousFamilleDTO> createSousFamille(@Valid @RequestBody SousFamilleDTO sousFamilleDTO, BindingResult bindingResult)
//            throws MethodArgumentNotValidException {
//        log.debug("REST request to save sousFamille : {}", sousFamilleDTO);
//       /* if (sousFamilleDTO.getCodqual() != null || !qualificationDTO.getCodqual().isEmpty()) {
//            bindingResult.addError(new FieldError("QualificationDTO", "codqual", "POST method does not accept " + ENTITY_NAME + " with code"));
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }*/
//        if (bindingResult.hasErrors()) {
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }
//        /*Ainsi, en passant null comme premier paramètre, l'exception est utilisée pour signaler une erreur de validation globale
//        sur la demande ou la méthode, et non une erreur spécifique liée à un paramètre de méthode individuel.*/
//        SousFamilleDTO result = sousFamilleService.save(sousFamilleDTO);
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
//    public ResponseEntity<SousFamilleDTO> updateSousFamille(@PathVariable Long id, @Valid @RequestBody SousFamilleDTO sousFamilleDTO)
//            throws MethodArgumentNotValidException {
//        log.debug("Request to update sousFamille: {}", id);
//        SousFamilleDTO result = sousFamilleService.update(sousFamilleDTO);
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
//    public ResponseEntity<SousFamilleDTO> getSousFamille(@PathVariable Long id) {
//        log.debug("Request to get sousFamille: {}", id);
//        SousFamilleDTO dto = sousFamilleService.getSousFamilleById(id);
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
//    public Collection<SousFamilleDTO> getAllSousFamilles() {
//        log.debug("Request to get all sousFamilles : {}");
//        return sousFamilleService.getAll();
//    }
//
//    /**
//     * DELETE /qualifications/{id} : deleteById the "id" qualification.
//     *
//     * @param id the id of the qualification to deleteById
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteSousFamille(@PathVariable Long id) {
//        log.debug("Request to deleteById sousFamille: {}", id);
//        sousFamilleService.delete(id);
//        return ResponseEntity.ok().build();
//    }
}
