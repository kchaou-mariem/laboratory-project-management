package com.labo.labo.controller;

import com.labo.labo.dto.PlageHoraireEquipementTacheDTO;
import com.labo.labo.entity.ClePlageHoraireEquipementTache;
import com.labo.labo.service.PlageHoraireEquipementTacheService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Collection;

@RestController
@RequestMapping("/plagesHoraire")
@CrossOrigin
public class PlageHoraireEquipementTacheController {

    private static final String ENTITY_NAME = "plageHoraireEquipementTache"; //remplacer plageHoraireEquipementTache par Entity_name dans le code
    private final PlageHoraireEquipementTacheService plageHoraireEquipementTacheService;
    private final Logger log = LoggerFactory.getLogger(PlageHoraireEquipementTacheService.class);

    public PlageHoraireEquipementTacheController(PlageHoraireEquipementTacheService plageHoraireEquipementTacheService) {
        this.plageHoraireEquipementTacheService = plageHoraireEquipementTacheService;
    }

    /**
     * POST /plageHoraireEquipementTaches : Create a new plageHoraireEquipementTache.
     *
     * @param plageHoraireEquipementTacheDTO
     * @param bindingResult Résultat de la liaison des données de la requête avec l'objet Java
     * @return the ResponseEntity with status 201 (Created) and with body the new qualification, or with status 400 (Bad Request) if the qualification has already an ID
     * @throws URISyntaxException                      if the Location URI syntax is incorrect
     * @throws org.springframework.web.bind.MethodArgumentNotValidException si l'objet avec @valid échoue à la validation
     */
    @PostMapping("/create")
    public ResponseEntity<PlageHoraireEquipementTacheDTO> createPlageHoraireEquipementTache(@Valid @RequestBody PlageHoraireEquipementTacheDTO plageHoraireEquipementTacheDTO, BindingResult bindingResult)
            throws MethodArgumentNotValidException {
        log.debug("REST request to save plageHoraireEquipementTache : {}", plageHoraireEquipementTacheDTO);
       /* if (plageHoraireEquipementTacheDTO.getCodqual() != null || !qualificationDTO.getCodqual().isEmpty()) {
            bindingResult.addError(new FieldError("QualificationDTO", "codqual", "POST method does not accept " + ENTITY_NAME + " with code"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }*/
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        /*Ainsi, en passant null comme premier paramètre, l'exception est utilisée pour signaler une erreur de validation globale
        sur la demande ou la méthode, et non une erreur spécifique liée à un paramètre de méthode individuel.*/
        PlageHoraireEquipementTacheDTO result = plageHoraireEquipementTacheService.save(plageHoraireEquipementTacheDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /*
     * PUT /qualifications : Updates an existing qualification.
     *
     * @param id
     * @param qualificationDTO the qualification to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated qualification,
     * or with status 400 (Bad Request) if the qualification is not valid,
     * or with status 500 (Internal Server Error) if the qualification couldn't be updated
     * @throws org.springframework.web.bind.MethodArgumentNotValidException
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<PlageHoraireEquipementTacheDTO> updatePlageHoraireEquipementTache(@PathVariable ClePlageHoraireEquipementTache id, @Valid @RequestBody PlageHoraireEquipementTacheDTO plageHoraireEquipementTacheDTO)
            throws MethodArgumentNotValidException {
        log.debug("Request to update plageHoraireEquipementTache: {}", id);
        PlageHoraireEquipementTacheDTO result = plageHoraireEquipementTacheService.update(plageHoraireEquipementTacheDTO);
        return ResponseEntity.ok().body(result);
    }

    /**
     * GET /qualifications/{id} : get the "id" qualification.
     *
     * @param id the id of the qualification to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body of qualification, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlageHoraireEquipementTacheDTO> getPlageHoraireEquipementTache(@PathVariable ClePlageHoraireEquipementTache id) {
        log.debug("Request to get plageHoraireEquipementTache: {}", id);
        PlageHoraireEquipementTacheDTO dto = plageHoraireEquipementTacheService.getPlageHoraireEquipementTacheById(id);
        //RestPreconditions.checkFound(dto, "qualification.NotFound");
        return ResponseEntity.ok().body(dto);
    }

    /**
     * GET /qualifications : get all the qualifications.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of qualifications in body
     */
    @GetMapping("/all")
    public Collection<PlageHoraireEquipementTacheDTO> getAllPlageHoraireEquipementTaches() {
        log.debug("Request to get all plageHoraireEquipementTaches : {}");
        return plageHoraireEquipementTacheService.getAll();
    }

    /**
     * DELETE /qualifications/{id} : deleteById the "id" qualification.
     *
     * @param id the id of the qualification to deleteById
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlageHoraireEquipementTache(@PathVariable ClePlageHoraireEquipementTache id) {
        log.debug("Request to deleteById plageHoraireEquipementTache: {}", id);
        plageHoraireEquipementTacheService.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
