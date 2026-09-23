package com.labo.labo.controller;

import com.labo.labo.dto.FamilleDTO;
import com.labo.labo.entity.Famille;
import com.labo.labo.entity.SousFamille;
import com.labo.labo.service.FamilleService;
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
@RequestMapping("/familles")
@CrossOrigin
public class FamilleController {
    //private static final String ENTITY_NAME = "famille"; //remplacer famille par Entity_name dans le code

    @Autowired
    FamilleService familleService;

    @PostMapping("/create")
    public Famille createFamille(@RequestBody Famille famille){
        return familleService.createFamille(famille);
    }

    @GetMapping("")
    public List<Famille> getAllFamilles(){

        return familleService.getAllFamilles();
    }

    @GetMapping("/{id}")
    public Optional<Famille> getFamille(@PathVariable Long id ){

        return familleService.getFamille(id);
    }

    @PutMapping("/update/{id}")
    public Famille updateFamille(@RequestBody Famille famille ,@PathVariable Long id ){
        return familleService.updateFamille(id,famille);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFamille(@PathVariable Long id){

        familleService.deleteFamille(id);
    }


//    /**
//     * POST /familles : Create a new famille.
//     *
//     * @param familleDTO
//     * @param bindingResult Résultat de la liaison des données de la requête avec l'objet Java
//     * @return the ResponseEntity with status 201 (Created) and with body the new qualification, or with status 400 (Bad Request) if the qualification has already an ID
//     * @throws URISyntaxException                      if the Location URI syntax is incorrect
//     * @throws org.springframework.web.bind.MethodArgumentNotValidException si l'objet avec @valid échoue à la validation
//     */
//    @PostMapping("/create")
//    public ResponseEntity<FamilleDTO> createFamille(@Valid @RequestBody FamilleDTO familleDTO, BindingResult bindingResult)
//            throws MethodArgumentNotValidException {
//        log.debug("REST request to save famille : {}", familleDTO);
//       /* if (familleDTO.getCodqual() != null || !qualificationDTO.getCodqual().isEmpty()) {
//            bindingResult.addError(new FieldError("QualificationDTO", "codqual", "POST method does not accept " + ENTITY_NAME + " with code"));
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }*/
//        if (bindingResult.hasErrors()) {
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }
//        /*Ainsi, en passant null comme premier paramètre, l'exception est utilisée pour signaler une erreur de validation globale
//        sur la demande ou la méthode, et non une erreur spécifique liée à un paramètre de méthode individuel.*/
//        FamilleDTO result = familleService.save(familleDTO);
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
//    public ResponseEntity<FamilleDTO> updateFamille(@PathVariable Long id, @Valid @RequestBody FamilleDTO familleDTO)
//            throws MethodArgumentNotValidException {
//        log.debug("Request to update famille: {}", id);
//        FamilleDTO result = familleService.update(familleDTO);
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
//    public ResponseEntity<FamilleDTO> getFamille(@PathVariable Long id) {
//        log.debug("Request to get famille: {}", id);
//        FamilleDTO dto = familleService.getFamilleById(id);
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
//    public Collection<FamilleDTO> getAllFamilles() {
//        log.debug("Request to get all familles : {}");
//        return familleService.getAll();
//    }
//
//    /**
//     * DELETE /qualifications/{id} : deleteById the "id" qualification.
//     *
//     * @param id the id of the qualification to deleteById
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteFamille(@PathVariable Long id) {
//        log.debug("Request to deleteById famille: {}", id);
//        familleService.delete(id);
//        return ResponseEntity.ok().build();
//    }
}
