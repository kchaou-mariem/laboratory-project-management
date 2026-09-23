package com.labo.labo.controller;
import com.labo.labo.dto.QuantiteDTO;
import com.labo.labo.entity.CleQuantite;
import com.labo.labo.service.QuantiteService;
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
@RequestMapping("/quantites")
@CrossOrigin
public class QuantiteController {
    private static final String ENTITY_NAME = "quantite"; //remplacer quantite par Entity_name dans le code
    private final QuantiteService quantiteService;
    private final Logger log = LoggerFactory.getLogger(QuantiteService.class);

    public QuantiteController(QuantiteService quantiteService) {
        this.quantiteService = quantiteService;
    }

    /**
     * POST /quantites : Create a new quantite.
     *
     * @param quantiteDTO
     * @param bindingResult Résultat de la liaison des données de la requête avec l'objet Java
     * @return the ResponseEntity with status 201 (Created) and with body the new qualification, or with status 400 (Bad Request) if the qualification has already an ID
     * @throws URISyntaxException                      if the Location URI syntax is incorrect
     * @throws org.springframework.web.bind.MethodArgumentNotValidException si l'objet avec @valid échoue à la validation
     */

    @PostMapping("/create")
    public ResponseEntity<QuantiteDTO> createquantite(@Valid @RequestBody QuantiteDTO quantiteDTO, BindingResult bindingResult)
            throws MethodArgumentNotValidException {
        log.debug("REST request to save quantite : {}", quantiteDTO);
       /* if (quantiteDTO.getCodqual() != null || !qualificationDTO.getCodqual().isEmpty()) {
            bindingResult.addError(new FieldError("QualificationDTO", "codqual", "POST method does not accept " + ENTITY_NAME + " with code"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }*/
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        /*Ainsi, en passant null comme premier paramètre, l'exception est utilisée pour signaler une erreur de validation globale
        sur la demande ou la méthode, et non une erreur spécifique liée à un paramètre de méthode individuel.*/
        QuantiteDTO result = quantiteService.save(quantiteDTO);
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
    public ResponseEntity<QuantiteDTO> updateQuantite(@PathVariable CleQuantite id, @Valid @RequestBody QuantiteDTO quantiteDTO)
            throws MethodArgumentNotValidException {
        log.debug("Request to update quantite: {}", id);
        QuantiteDTO result = quantiteService.update(quantiteDTO);
        return ResponseEntity.ok().body(result);
    }

    /**
     * GET /qualifications/{id} : get the "id" qualification.
     *
     * @param id the id of the qualification to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body of qualification, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<QuantiteDTO> getQuantite(@PathVariable CleQuantite id) {
        log.debug("Request to get quantite: {}", id);
        QuantiteDTO dto = quantiteService.getQuantiteById(id);
        //RestPreconditions.checkFound(dto, "qualification.NotFound");
        return ResponseEntity.ok().body(dto);
    }

    /**
     * GET /qualifications : get all the qualifications.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of qualifications in body
     */
    @GetMapping("/all")
    public Collection<QuantiteDTO> getAllQuantites() {
        log.debug("Request to get all quantites : {}");
        return quantiteService.getAll();
    }

    /**
     * DELETE /qualifications/{id} : deleteById the "id" qualification.
     *
     * @param id the id of the qualification to deleteById
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuantite(@PathVariable CleQuantite id) {
        log.debug("Request to deleteById quantite: {}", id);
        quantiteService.delete(id);
        return ResponseEntity.ok().build();
    }
    
    
}
