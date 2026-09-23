package com.labo.labo.controller;

import com.labo.labo.dto.FichierDTO;
import com.labo.labo.entity.Fichier;
import com.labo.labo.entity.Formule;
import com.labo.labo.service.FichierService;
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
@RequestMapping(path = "/fichiers")
@CrossOrigin
public class FichierController {
  @Autowired
    FichierService fichierService;

    @PostMapping("/create")
    public Fichier createFichier(@RequestBody Fichier fichier){
        return fichierService.createFichier(fichier);
    }
    @GetMapping("")
    public List<Fichier> getAllFichiers(){
        return fichierService.getAllFichiers();
    }
    @GetMapping("/{id}")
    public Optional<Fichier> getFichier(@PathVariable Long id ){
        return fichierService.getFichier(id);
    }
    @PutMapping("/update/{id}")
    public Fichier updateFichier(@RequestBody Fichier fichier ,@PathVariable Long id ){
        return fichierService.updateFichier(id,fichier);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteFichier(@PathVariable Long id){
        fichierService.deleteFichier(id);
    }


//    /**
//     * POST /fichiers : Create a new fichier.
//     *
//     * @param fichierDTO
//     * @param bindingResult Résultat de la liaison des données de la requête avec l'objet Java
//     * @return the ResponseEntity with status 201 (Created) and with body the new qualification, or with status 400 (Bad Request) if the qualification has already an ID
//     * @throws URISyntaxException                      if the Location URI syntax is incorrect
//     * @throws org.springframework.web.bind.MethodArgumentNotValidException si l'objet avec @valid échoue à la validation
//     */
//    @PostMapping("/create")
//    public ResponseEntity<FichierDTO> createfichier(@Valid @RequestBody FichierDTO fichierDTO, BindingResult bindingResult)
//            throws MethodArgumentNotValidException {
//        log.debug("REST request to save fichier : {}", fichierDTO);
//       /* if (fichierDTO.getCodqual() != null || !qualificationDTO.getCodqual().isEmpty()) {
//            bindingResult.addError(new FieldError("QualificationDTO", "codqual", "POST method does not accept " + ENTITY_NAME + " with code"));
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }*/
//        if (bindingResult.hasErrors()) {
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }
//        /*Ainsi, en passant null comme premier paramètre, l'exception est utilisée pour signaler une erreur de validation globale
//        sur la demande ou la méthode, et non une erreur spécifique liée à un paramètre de méthode individuel.*/
//        FichierDTO result = fichierService.save(fichierDTO);
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
//    public ResponseEntity<FichierDTO> updateFichier(@PathVariable Long id, @Valid @RequestBody FichierDTO fichierDTO)
//            throws MethodArgumentNotValidException {
//        log.debug("Request to update fichier: {}", id);
//        FichierDTO result = fichierService.update(fichierDTO);
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
//    public ResponseEntity<FichierDTO> getFichier(@PathVariable Long id) {
//        log.debug("Request to get fichier: {}", id);
//        FichierDTO dto = fichierService.getFichierById(id);
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
//    public Collection<FichierDTO> getAllFichiers() {
//        log.debug("Request to get all fichiers : {}");
//        return fichierService.getAll();
//    }
//
//    /**
//     * DELETE /qualifications/{id} : deleteById the "id" qualification.
//     *
//     * @param id the id of the qualification to deleteById
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteFichier(@PathVariable Long id) {
//        log.debug("Request to deleteById fichier: {}", id);
//        fichierService.delete(id);
//        return ResponseEntity.ok().build();
//    }
}
