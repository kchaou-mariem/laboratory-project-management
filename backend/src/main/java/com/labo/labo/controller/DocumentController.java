package com.labo.labo.controller;


import com.labo.labo.dto.DocumentDTO;
import com.labo.labo.entity.Annonce;
import com.labo.labo.entity.Document;
import com.labo.labo.entity.QDocument;
import com.labo.labo.service.DocumentService;
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

@RequestMapping("/documents")
@RestController
@CrossOrigin
public class DocumentController {
@Autowired
    DocumentService documentService;

    @PostMapping("/create")
    public Document createDocument(@RequestBody Document document){
        return documentService.createDocument(document);
    }

    @GetMapping("")
    public List<Document> getAllDocument(){

        return documentService.getAllDocuments();
    }

    @GetMapping("/{id}")
    public Optional<Document> getDocument(@PathVariable Long id ){

        return documentService.getDocument(id);
    }

    @PutMapping("/update/{id}")
    public Document updateDocument(@RequestBody Document document ,@PathVariable Long id ){
        return documentService.updateDocument(id,document);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDocument(@PathVariable Long id){

        documentService.deleteDocument(id);
    }






//    private static final String ENTITY_NAME = "document"; //remplacer document par Entity_name dans le code
//    private final DocumentService documentService;
//    private final Logger log = LoggerFactory.getLogger(DocumentService.class);
//
//    public DocumentController(DocumentService documentService) {
//        this.documentService = documentService;
//    }
//
//    /**
//     * POST /documents : Create a new document.
//     *
//     * @param documentDTO
//     * @param bindingResult Résultat de la liaison des données de la requête avec l'objet Java
//     * @return the ResponseEntity with status 201 (Created) and with body the new qualification, or with status 400 (Bad Request) if the qualification has already an ID
//     * @throws URISyntaxException                      if the Location URI syntax is incorrect
//     * @throws org.springframework.web.bind.MethodArgumentNotValidException si l'objet avec @valid échoue à la validation
//     */
//    @PostMapping("/create")
//    public ResponseEntity<DocumentDTO> createDocument(@Valid @RequestBody DocumentDTO documentDTO, BindingResult bindingResult)
//            throws MethodArgumentNotValidException {
//        log.debug("REST request to save document : {}", documentDTO);
//       /* if (documentDTO.getCodqual() != null || !qualificationDTO.getCodqual().isEmpty()) {
//            bindingResult.addError(new FieldError("QualificationDTO", "codqual", "POST method does not accept " + ENTITY_NAME + " with code"));
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }*/
//        if (bindingResult.hasErrors()) {
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }
//        /*Ainsi, en passant null comme premier paramètre, l'exception est utilisée pour signaler une erreur de validation globale
//        sur la demande ou la méthode, et non une erreur spécifique liée à un paramètre de méthode individuel.*/
//        DocumentDTO result = documentService.save(documentDTO);
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
//    public ResponseEntity<DocumentDTO> updateDocument(@PathVariable Long id, @Valid @RequestBody DocumentDTO documentDTO)
//            throws MethodArgumentNotValidException {
//        log.debug("Request to update document: {}", id);
//        DocumentDTO result = documentService.update(documentDTO);
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
//    public ResponseEntity<DocumentDTO> getDocument(@PathVariable Long id) {
//        log.debug("Request to get document: {}", id);
//        DocumentDTO dto = documentService.getDocumentById(id);
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
//    public Collection<DocumentDTO> getAllDocuments() {
//        log.debug("Request to get all documents : {}");
//        return documentService.getAll();
//    }
//
//    /**
//     * DELETE /qualifications/{id} : deleteById the "id" qualification.
//     *
//     * @param id the id of the qualification to deleteById
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
//        log.debug("Request to deleteById document: {}", id);
//        documentService.delete(id);
//        return ResponseEntity.ok().build();
//    }
}
