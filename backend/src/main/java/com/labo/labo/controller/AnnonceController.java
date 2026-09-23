package com.labo.labo.controller;

import com.labo.labo.dto.AnnonceDTO;
import com.labo.labo.entity.Annonce;
import com.labo.labo.entity.Phase;
import com.labo.labo.service.AnnonceService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequestMapping("/annonces")
@RestController
@CrossOrigin
public class AnnonceController {
//    private static final St4
//    ring ENTITY_NAME = "annonce"; //remplacer annonce par Entity_name dans le code
    @Autowired
     AnnonceService annonceService;
//    private final Logger log = LoggerFactory.getLogger(AnnonceService.class);
//
//    public AnnonceController(AnnonceService annonceService) {
//
//        this.annonceService = annonceService;
//    }

    @PostMapping("/create")
    public Annonce createAnnonce(@RequestBody Annonce annonce){
        return annonceService.createAnnonce(annonce);
    }

    @GetMapping("")
    public List<Annonce> getAllAnnonce(){

        return annonceService.getAllAnnonces();
    }

    @GetMapping("/{id}")
    public Optional<Annonce> getAnnonce(@PathVariable Long id ){

        return annonceService.getAnnonce(id);
    }

    @PutMapping("/update/{id}")
    public Annonce updateAnnonce(@RequestBody Annonce annonce ,@PathVariable Long id ){
        return annonceService.updateAnnonce(id,annonce);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAnnonce(@PathVariable Long id){

        annonceService.deleteAnnonce(id);
    }


//    /**
//     * POST /annonces : Create a new annonce.
//     *
//     * @param annonceDTO
//     * @param bindingResult Résultat de la liaison des données de la requête avec l'objet Java
//     * @return the ResponseEntity with status 201 (Created) and with body the new qualification, or with status 400 (Bad Request) if the qualification has already an ID
//     * @throws URISyntaxException  if the Location URI syntax is incorrect
//     * @throws org.springframework.web.bind.MethodArgumentNotValidException si l'objet avec @valid échoue à la validation
//     */
//
//    @PostMapping("/create")
//    public ResponseEntity<AnnonceDTO> createAnnonce(@Valid @RequestBody AnnonceDTO annonceDTO, BindingResult bindingResult)
//            throws  MethodArgumentNotValidException {
//        log.debug("REST request to save annonce : {}", annonceDTO);
//       /* if (annonceDTO.getCodqual() != null || !qualificationDTO.getCodqual().isEmpty()) {
//            bindingResult.addError(new FieldError("QualificationDTO", "codqual", "POST method does not accept " + ENTITY_NAME + " with code"));
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }*/
//        if (bindingResult.hasErrors()) {
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }
//        /*Ainsi, en passant null comme premier paramètre, l'exception est utilisée pour signaler une erreur de validation globale
//        sur la demande ou la méthode, et non une erreur spécifique liée à un paramètre de méthode individuel.*/
//        AnnonceDTO result = annonceService.save(annonceDTO);
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
//    public ResponseEntity<AnnonceDTO> updateAnnonce(@PathVariable Long id, @Valid @RequestBody AnnonceDTO annonceDTO)
//            throws MethodArgumentNotValidException {
//        log.debug("Request to update annonce: {}", id);
//        //DTO.setCodqual(id);
//        AnnonceDTO result = annonceService.update(annonceDTO);
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
//    public ResponseEntity<AnnonceDTO> getAnnonce(@PathVariable Long id) {
//        log.debug("Request to get annonce: {}", id);
//        AnnonceDTO dto = annonceService.getAnnonceById(id);
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
//    public Collection<AnnonceDTO> getAllAnnonces() {
//        log.debug("Request to get all Annonces : {}");
//        return annonceService.getAll();
//    }
//
//    /**
//     * DELETE /qualifications/{id} : deleteById the "id" qualification.
//     *
//     * @param id the id of the qualification to deleteById
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteAnnonce(@PathVariable Long id) {
//        log.debug("Request to deleteById Annonce: {}", id);
//        annonceService.delete(id);
//        return ResponseEntity.ok().build();
//    }
}

