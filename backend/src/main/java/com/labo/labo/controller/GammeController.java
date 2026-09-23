package com.labo.labo.controller;

import com.labo.labo.dto.GammeDTO;
import com.labo.labo.entity.Famille;
import com.labo.labo.entity.Gamme;
import com.labo.labo.service.GammeService;
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
@RequestMapping("/gammes")
@CrossOrigin
public class GammeController {
  //  private static final String ENTITY_NAME = "gamme"; //remplacer gamme par Entity_name dans le code
    @Autowired
    GammeService gammeService;

    @PostMapping("/create")
    public Gamme createGamme(@RequestBody Gamme gamme){
        return gammeService.createGamme(gamme);
    }

    @GetMapping("")
    public List<Gamme> getAllGammes(){

        return gammeService.getAllGammes();
    }

    @GetMapping("/{id}")
    public Optional<Gamme> getGamme(@PathVariable Long id ){

        return gammeService.getGamme(id);
    }

    @PutMapping("/update/{id}")
    public Gamme updateGamme(@RequestBody Gamme gamme ,@PathVariable Long id ){
        return gammeService.updateGamme(id,gamme);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGamme(@PathVariable Long id){
        gammeService.deleteGamme(id);
    }

//
//    /**
//     * POST /gammes : Create a new gamme.
//     *
//     * @param gammeDTO
//     * @param bindingResult Résultat de la liaison des données de la requête avec l'objet Java
//     * @return the ResponseEntity with status 201 (Created) and with body the new qualification, or with status 400 (Bad Request) if the qualification has already an ID
//     * @throws URISyntaxException                      if the Location URI syntax is incorrect
//     * @throws org.springframework.web.bind.MethodArgumentNotValidException si l'objet avec @valid échoue à la validation
//     */
//    @PostMapping("/create")
//    public ResponseEntity<GammeDTO> createGamme(@Valid @RequestBody GammeDTO gammeDTO, BindingResult bindingResult)
//            throws MethodArgumentNotValidException {
//        log.debug("REST request to save gamme : {}", gammeDTO);
//       /* if (gammeDTO.getCodqual() != null || !qualificationDTO.getCodqual().isEmpty()) {
//            bindingResult.addError(new FieldError("QualificationDTO", "codqual", "POST method does not accept " + ENTITY_NAME + " with code"));
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }*/
//        if (bindingResult.hasErrors()) {
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }
//        /*Ainsi, en passant null comme premier paramètre, l'exception est utilisée pour signaler une erreur de validation globale
//        sur la demande ou la méthode, et non une erreur spécifique liée à un paramètre de méthode individuel.*/
//        GammeDTO result = gammeService.save(gammeDTO);
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
//    public ResponseEntity<GammeDTO> updateGamme(@PathVariable Long id, @Valid @RequestBody GammeDTO gammeDTO)
//            throws MethodArgumentNotValidException {
//        log.debug("Request to update gamme: {}", id);
//        GammeDTO result = gammeService.update(gammeDTO);
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
//    public ResponseEntity<GammeDTO> getGamme(@PathVariable Long id) {
//        log.debug("Request to get gamme: {}", id);
//        GammeDTO dto = gammeService.getGammeById(id);
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
//    public Collection<GammeDTO> getAllGammes() {
//        log.debug("Request to get all gammes : {}");
//        return gammeService.getAll();
//    }
//
//    /**
//     * DELETE /qualifications/{id} : deleteById the "id" qualification.
//     *
//     * @param id the id of the qualification to deleteById
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteGamme(@PathVariable Long id) {
//        log.debug("Request to deleteById gamme: {}", id);
//        gammeService.delete(id);
//        return ResponseEntity.ok().build();
//    }
}
