package com.labo.labo.service;

import com.labo.labo.dto.CompteDTO;
import com.labo.labo.dto.DocumentDTO;
import com.labo.labo.entity.Annonce;
import com.labo.labo.entity.Compte;
import com.labo.labo.entity.Document;
import com.labo.labo.factory.CompteFactory;
import com.labo.labo.factory.DocumentFactory;
import com.labo.labo.repository.CompteRepository;
import com.labo.labo.repository.DocumentRepository;
//import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Optional<Document> getDocument (long id){
        return documentRepository.findById(id);
    }

    public Document updateDocument(Long id, Document documentDetails) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Document non trouvée avec l'id: " + id));
        document.setDescription(documentDetails.getDescription());
        document.setNom(documentDetails.getNom());

        return documentRepository.save(document);
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }

//    public DocumentDTO save(DocumentDTO documentDTO) {
//       log.debug("Request to save document: {}", documentDTO);
//        Document document = DocumentFactory.documentDTOToDocument(documentDTO);
//       /* Optional<Document> existingDocument=documentRepository.findById(document.getIdDocument()); //findById return optional par défaut
//        if(existingDocument.isPresent())
//        { throw new IllegalStateException("document existe !");}*/
//
//        Document existingDocument=documentRepository.findByNom(document.getNom()); //findById return optional par défaut
//        if(existingDocument!=null)
//        { throw new IllegalStateException("document existe !");}
//        document = documentRepository.save(document);
//        return DocumentFactory.documentToDocumentDTO(document);
//    }
//
//    public DocumentDTO update(DocumentDTO documentDTO) {
//        log.debug("Request to update document: {}", documentDTO);
//        Document document = DocumentFactory.documentDTOToDocument(documentDTO);
//        Document inBase=documentRepository.findByNom(document.getNom());
//        if(inBase==null)
//        { throw new IllegalStateException("document n'existe pas !");}
//        //Preconditions.checkLegalArgument(inBase != null, "service.NotFound");
//        //Document inBase = inBaseOpt.get(); //transformer optional en entité
//        inBase.setNom(documentDTO.getNom());
//        inBase.setDescription(documentDTO.getDescription());
//        document = documentRepository.save(document);
//        return DocumentFactory.documentToDocumentDTO(document);
//    }
//
//    @Transactional(readOnly = true)
//    public DocumentDTO getDocumentById(Long id) { //return dto
//        log.debug("Request to get document: {}", id);
//        Optional<Document> documentOpt = documentRepository.findById(id);
//        if(documentOpt.isEmpty())
//        { throw new IllegalStateException("document n'existe pas !");}
//        Document document=documentOpt.get();
//        return DocumentFactory.documentToDocumentDTO(document);
//    }
//
//    @Transactional(readOnly = true)
//    public Document getdDocument(Long id) {   //return entité
//        log.debug("Request to get document: {}", id);
//        Optional<Document> documentOpt = documentRepository.findById(id);
//        if(documentOpt.isEmpty())
//        { throw new IllegalStateException("document n'existe pas !");}
//        return documentOpt.get();
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<DocumentDTO> getAll() {
//        log.debug("Request to get All document");
//        Collection<Document> result = documentRepository.findAll();
//        return DocumentFactory.documentToDocumentDTOs(result);
//    }
//    @Transactional(readOnly = true)
//    public DocumentDTO getDocumentByNom(String nom) { //return dto
//        log.debug("Request to get document: {}", nom);
//        Document document = documentRepository.findByNom(nom);
//        if(document==null)
//        { throw new IllegalStateException("document n'existe pas !");}
//        return DocumentFactory.documentToDocumentDTO(document);
//    }
//    @Transactional(readOnly = true)
//    public DocumentDTO getDocumentByNomProjetAndNomDocument(String nomP, String nomD) { //return dto
//        log.debug("Request to get document: {}", nomP,nomD);
//        Document document = documentRepository.findByProjetNomAndNom(nomP,nomD);
//        if(document==null)
//        { throw new IllegalStateException("document n'existe pas !");}
//        return DocumentFactory.documentToDocumentDTO(document);
//    }
//
//    public void delete(Long id) {
//        log.debug("Request to delete document: {}", id);
//        Optional<Document> documentOpt = documentRepository.findById(id);
//        if(documentOpt.isEmpty())
//        { throw new IllegalStateException("document n'existe pas !");}
//        documentRepository.deleteById(id);
//    }
//
//    /*private void checkdocumentExists(String titre) {
//        if (documentRepository.findByTitre(titre) != null) {
//            throw new IllegalStateException("document with titre " + titre + " already exists");
//        }*/

}
