package com.labo.labo.controller;



import com.labo.labo.dto.MarqueDTO;
import com.labo.labo.entity.Marque;
import com.labo.labo.entity.Phase;
import com.labo.labo.service.MarqueService;
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
@RequestMapping("/marques")
@CrossOrigin
public class MarqueController {
    @Autowired
    MarqueService marqueService;

    @PostMapping("/create")
    public Marque createMarque(@RequestBody Marque marque){

        return marqueService.createMarque(marque);
    }

    @GetMapping("")
    public List<Marque> getAllMarque(){

        return marqueService.getAllMarques();
    }

    @GetMapping("/{id}")
    public Optional<Marque> getMarque(@PathVariable Long id ){

        return marqueService.getMarque(id);
    }

    // @CrossOrigin(origins = "http://localhost:5173/updatePhase")
    @PutMapping("/update/{id}")
    public Marque updateMarque(@RequestBody Marque marque ,@PathVariable Long id ){
        return marqueService.updateMarque(id,marque);
    }



    @DeleteMapping("/delete/{id}")
    public void deleteMarque(@PathVariable Long id){

        marqueService.deleteMarque(id);
    }
//
//    /**
//     * POST /marques : Create a new marque.
//     *
//     * @param marqueDTO
//     * @param bindingResult Résultat de la liaison des données de la requête avec l'objet Java
//     * @return the ResponseEntity with status 201 (Created) and with body the new qualification, or with status 400 (Bad Request) if the qualification has already an ID
//     * @throws URISyntaxException                      if the Location URI syntax is incorrect
//     * @throws org.springframework.web.bind.MethodArgumentNotValidException si l'objet avec @valid échoue à la validation
//     */
//    @PostMapping("/create")
//    public ResponseEntity<MarqueDTO> createmarque(@Valid @RequestBody MarqueDTO marqueDTO, BindingResult bindingResult)
//            throws MethodArgumentNotValidException {
//        log.debug("REST request to save marque : {}", marqueDTO);
//       /* if (marqueDTO.getCodqual() != null || !qualificationDTO.getCodqual().isEmpty()) {
//            bindingResult.addError(new FieldError("QualificationDTO", "codqual", "POST method does not accept " + ENTITY_NAME + " with code"));
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }*/
//        if (bindingResult.hasErrors()) {
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }
//        /*Ainsi, en passant null comme premier paramètre, l'exception est utilisée pour signaler une erreur de validation globale
//        sur la demande ou la méthode, et non une erreur spécifique liée à un paramètre de méthode individuel.*/
//        MarqueDTO result = marqueService.save(marqueDTO);
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
//    public ResponseEntity<MarqueDTO> updateMarque(@PathVariable Long id, @Valid @RequestBody MarqueDTO marqueDTO)
//            throws MethodArgumentNotValidException {
//        log.debug("Request to update marque: {}", id);
//        MarqueDTO result = marqueService.update(marqueDTO);
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
//    public ResponseEntity<MarqueDTO> getMarque(@PathVariable Long id) {
//        log.debug("Request to get marque: {}", id);
//        MarqueDTO dto = marqueService.getMarqueById(id);
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
//    public Collection<MarqueDTO> getAllMarques() {
//        log.debug("Request to get all marques : {}");
//        return marqueService.getAll();
//    }
//
//    /**
//     * DELETE /qualifications/{id} : deleteById the "id" qualification.
//     *
//     * @param id the id of the qualification to deleteById
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteMarque(@PathVariable Long id) {
//        log.debug("Request to deleteById marque: {}", id);
//        marqueService.delete(id);
//        return ResponseEntity.ok().build();
//    }
}
