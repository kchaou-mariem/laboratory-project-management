package com.labo.labo.factory;

import com.labo.labo.dto.DocumentDTO;
import com.labo.labo.entity.Document;
import com.labo.labo.entity.Fichier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DocumentFactory {
    public static DocumentDTO documentToDocumentDTO(Document document) {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setNom(document.getNom());
        documentDTO.setDescription(document.getDescription());
//        List<String> nomsFichiers = new ArrayList<>();
//        for(Fichier fichier : document.getFichiers()) {
//            nomsFichiers.add(fichier.getNom());
//        }
//        documentDTO.setFichiers(nomsFichiers);
        return documentDTO;
    }

    public static Document documentDTOToDocument(DocumentDTO documentDTO) {
        Document document = new Document();
        document.setNom(documentDTO.getNom());
        document.setDescription(documentDTO.getDescription());
//        List<Fichier> fichiers = new ArrayList<>();
//        for(String nomFichier : documentDTO.getFichiers()) {
//            Fichier fichier = new Fichier();
//            fichier.setNom(nomFichier);
//            fichiers.add(fichier);
//        }
//        document.setFichiers(fichiers);
        return document;
    }

    public static Collection<DocumentDTO> documentToDocumentDTOs(Collection<Document> documents) {
        List<DocumentDTO> documentsDTO = new ArrayList<>();
        documents.forEach(x -> documentsDTO.add(documentToDocumentDTO(x)));
        return documentsDTO;
    }
}
