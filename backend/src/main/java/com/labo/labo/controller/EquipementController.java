package com.labo.labo.controller;


import com.labo.labo.dto.EquipementDTO;
import com.labo.labo.service.EquipementService;
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

public class EquipementController {

    private static final String ENTITY_NAME = "equipement"; //remplacer equipement par Entity_name dans le code
    private final EquipementService equipementService;
    private final Logger log = LoggerFactory.getLogger(EquipementService.class);

    public EquipementController(EquipementService equipementService) {
        this.equipementService = equipementService;
    }

//    /**
//     * POST /equipements : Create a new equipement.
//     *
//     * @param equipementDTO
//     * @param bindingResult Résultat de la liaison des données de la requête avec l'objet Java
//     * @return the ResponseEntity with status 201 (Created) and with body the new qualification, or with status 400 (Bad Request) if the qualification has already an ID
//     * @throws URISyntaxException                      if the Location URI syntax is incorrect
//     * @throws org.springframework.web.bind.MethodArgumentNotValidException si l'objet avec @valid échoue à la validation
//     */
//    @PostMapping("/create")
//    public ResponseEntity<EquipementDTO> createEquipement(@Valid @RequestBody EquipementDTO equipementDTO, BindingResult bindingResult)
//            throws MethodArgumentNotValidException {
//        log.debug("REST request to save equipement : {}", equipementDTO);
//       /* if (equipementDTO.getCodqual() != null || !qualificationDTO.getCodqual().isEmpty()) {
//            bindingResult.addError(new FieldError("QualificationDTO", "codqual", "POST method does not accept " + ENTITY_NAME + " with code"));
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }*/
//        if (bindingResult.hasErrors()) {
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }
//        /*Ainsi, en passant null comme premier paramètre, l'exception est utilisée pour signaler une erreur de validation globale
//        sur la demande ou la méthode, et non une erreur spécifique liée à un paramètre de méthode individuel.*/
//        EquipementDTO result = equipementService.save(equipementDTO);
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
//    public ResponseEntity<EquipementDTO> updateEquipement(@PathVariable Long id, @Valid @RequestBody EquipementDTO equipementDTO)
//            throws MethodArgumentNotValidException {
//        log.debug("Request to update equipement: {}", id);
//        EquipementDTO result = equipementService.update(equipementDTO);
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
//    public ResponseEntity<EquipementDTO> getEquipement(@PathVariable Long id) {
//        log.debug("Request to get equipement: {}", id);
//        EquipementDTO dto = equipementService.getEquipementById(id);
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
//    public Collection<EquipementDTO> getAllEquipements() {
//        log.debug("Request to get all equipements : {}");
//        return equipementService.getAll();
//    }
//
//    /**
//     * DELETE /qualifications/{id} : deleteById the "id" qualification.
//     *
//     * @param id the id of the qualification to deleteById
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteEquipement(@PathVariable Long id) {
//        log.debug("Request to deleteById equipement: {}", id);
//        equipementService.delete(id);
//        return ResponseEntity.ok().build();
//    }
}
