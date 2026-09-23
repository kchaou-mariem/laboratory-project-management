package com.labo.labo;

import com.labo.labo.entity.*;
import com.labo.labo.factory.*;
import com.labo.labo.repository.DetailsPhaseRepository;
import com.labo.labo.service.*;
import com.labo.labo.dto.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@SpringBootApplication
public class GestionProjetLaboApplication {

	public static void main(String[] args) {

		SpringApplication.run(GestionProjetLaboApplication.class, args);
//		ApplicationContext ctx = SpringApplication.run(GestionProjetLaboApplication.class, args);

//	ProjetService projetService=ctx.getBean(ProjetService.class);
//		CompteService compteService=ctx.getBean(CompteService.class);
//		SousFamilleService sousFamilleService=ctx.getBean(SousFamilleService.class);
//		FamilleService familleService=ctx.getBean(FamilleService.class);
//		GammeService gammeService=ctx.getBean(GammeService.class);
//		MarqueService marqueService=ctx.getBean(MarqueService.class);
//		AnnonceService annonceService=ctx.getBean(AnnonceService.class);
//		DocumentService documentService=ctx.getBean(DocumentService.class);
//		FichierService fichierService=ctx.getBean(FichierService.class);
//		EquipementService EquipementService=ctx.getBean(EquipementService.class);
//		PlageHoraireEquipementTacheService plageHoraireEquipementTacheService=ctx.getBean(PlageHoraireEquipementTacheService.class);
//		TacheService tacheService=ctx.getBean(TacheService.class);
//		DetailsPhaseService detailsPhaseService =ctx.getBean(DetailsPhaseService.class);
//		FormuleService formuleService=ctx.getBean(FormuleService.class);
//		IngredientService ingredientService=ctx.getBean(IngredientService.class);
//		QuantiteService quantiteService=ctx.getBean(QuantiteService.class);
//		PhaseService phaseService=ctx.getBean(PhaseService.class);
//
//		DetailsPhaseRepository detailsPhaseRepository =ctx.getBean(DetailsPhaseRepository.class);

		////////////////////////////////////////////////////detailsPhase//////////////////////////////////////////////////////////

//		Projet p =new Projet();
//		Formule f1 = new Formule();
//		Formule f2=new Formule();
//		Tache t=new Tache();
//		Collection<Formule> formuleList = new ArrayList<>();
//		formuleList.add(f1);
//		formuleList.add(f2);
//		Collection<Projet> projetList = new ArrayList<>();
//		projetList.add(p);
//		Collection<Tache> tacheList = new ArrayList<>();
//		tacheList.add(t);
		//StatutPhase s;



		/*DetailsPhase detailsPhase=new DetailsPhase("p4", LocalDate.of(2024,12,3),LocalDate.of(2024,12,5),14,0,0,0,StatutPhase.en_cours,projetList, formuleList,tacheList);
       // DetailsPhase phase1=detailsPhaseRepository.save(detailsPhase);
		DetailsPhaseDTO phaseDTO = DetailsPhaseFactory.phaseToPhaseDTO(detailsPhase);
		phaseService.save(phaseDTO);
		//String statutValue = StatutPhase.TERMINE.getValeur();*/

//Phase phase=new Phase("nomPhase1");
//PhaseDTO phaseDTO=PhaseFactory.phaseToPhaseDTO(phase);
//phaseService.save(phaseDTO);
		//////////create
//		DetailsPhase detailsPhase =new DetailsPhase("p1", LocalDate.of(2024,11,3),LocalDate.of(2024,12,14),20,0,0,0,StatutPhase.A_VENIR,formuleList,tacheList,phase);
		//DetailsPhase detailsPhase=new DetailsPhase();
//		DetailsPhaseDTO phaseD = DetailsPhaseFactory.phaseToPhaseDTO(detailsPhase);
//		detailsPhaseService.save(phaseD);

//detailsPhaseService.delete(DetailsPhaseFactory.phaseDTOToPhase(phaseD));
/*
		///////update
		DetailsPhaseDTO phaseDto = phaseService.getPhaseByNom("p1");
		// Modification du temps réalisé de la detailsPhase
		int nouveauTempsRealise = 3; // Remplacez 10 par la nouvelle valeur du temps réalisé
		phaseDto.setTempsRealise(nouveauTempsRealise);
		phaseDto.setDateDebut(LocalDate.of(2024,05,12));
		phaseDto.setDateFin(LocalDate.of(2024,06,12));
		// Appel de la méthode update existante pour mettre à jour la detailsPhase
		phaseService.update(phaseDto);

*/

//////////////////////////////////////////////////////projet////////////////////////////////////////////
//	SousFamille sf=new SousFamille("sf1");
		//SousFamilleDTO sousFamilleDTO = SousFamilleFactory.sousFamilleToSousFamilleDTO(sf);
		//sousFamilleService.save(sousFamilleDTO);
//		Gamme g=new Gamme("g1");
		//GammeDTO gammeDTO = GammeFactory.gammeToGammeDTO(g);
		//gammeService.save(gammeDTO);
//		Famille fa=new Famille("f1");
		//FamilleDTO familleDTO = FamilleFactory.familleToFamilleDTO(fa);
		//familleService.save(familleDTO);
//		Marque m=new Marque("m1");
		//MarqueDTO marqueDTO = MarqueFactory.marqueToMarqueDTO(m);
		//marqueService.save(marqueDTO);
//		Annonce a=new Annonce();
//		Collection<Annonce> annonceList = new ArrayList<>();
//		annonceList.add(a);
//		Document d=new Document();
//		Collection<Document> documentList = new ArrayList<>();
//		documentList.add(d);
//		Fichier fic =new Fichier();
//		Collection<Fichier> fichierList = new ArrayList<>();
//		fichierList.add(fic);
//		DetailsPhase detailsPhaseP =new DetailsPhase();
//		Collection<DetailsPhase> detailsPhaseList = new ArrayList<>();
//		detailsPhaseList.add(detailsPhaseP);
//		Formule formule =new Formule();
//		Collection<Formule> formulePList = new ArrayList<>();
//		formuleList.add(formule);
//		Compte cpte =new Compte();
//		Collection<Compte> compteList = new ArrayList<>();
//		compteList.add(cpte);
//		Tache tache =new Tache();
//		Collection<Tache> tachePList = new ArrayList<>();
//		tacheList.add(tache);
//		List<String>PARQUI= new ArrayList<>();
//		PARQUI.add("mohamed");
//		PARQUI.add("Mariem");
//
//		Projet projet=new Projet("projet1",LocalDate.of(2024,5,19),LocalDate.of(2025,4,19),24,0,0,0,"description",PARQUI,"by reunion",Statut.REJETE,sf,fa,g,m,annonceList,documentList,fichierList,formulePList,compteList,tachePList);

		/*Projet projet=new Projet("projet1",LocalDate.of(2023,03,19),LocalDate.of(2024,03,19),100,0,0,0,"description",PARQUI,"by reunion",Statut.REJETE,annonceList,documentList,fichierList,detailsPhaseList,formulePList,compteList,tachePList);
		projet.setSousFamille(sf);
		projet.setFamille(fa);
		projet.setGamme(g);
		projet.setMarque(m);*/

//	ProjetDTO projetDTO = ProjetFactory.projetToProjetDTO(projet);
//		projetService.save(projetDTO);

		///update

		/*ProjetDTO projetD = projetService.getProjetByNom("projet1");
		// Modification du temps réalisé de la detailsPhase
		int nouveauTempsRealiseP = 30; // Remplacez 10 par la nouvelle valeur du temps réalisé
		projetD.setTempsRealise(nouveauTempsRealiseP);
		projetD.setDateDebut(LocalDate.of(2024,06,12));
		projetD.setDateFin(LocalDate.of(2024,10,12));
		// Appel de la méthode update existante pour mettre à jour la detailsPhase
		projetService.update(projetD);
*/
		Collection<Equipement> equipementList=new ArrayList<>();


		//DetailsPhaseDTO phaseDTO = phaseService.getPhaseByNom("p1");//pour éviter la duplication normalement // Ou tout autre moyen de récupération de la detailsPhase existante

		// Créer une nouvelle tâche en associant la detailsPhase existante
		//Tache tache1 = new Tache(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 2, 29), "tache1", "description", 10, 0, 0, 0, Statut.TERMINE, DetailsPhaseFactory.phaseDTOToPhase(phaseDTO), projet, equipementList, compteList);
		/*Tache tache1 = new Tache(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 2, 29), "tache1", "description", 10, 0, 0, 0, Statut.TERMINE,detailsPhase, projet, equipementList, compteList);
		//tache1.setDetailsPhase(detailsPhase);
		TacheDTO tacheDTO = TacheFactory.tacheToTacheDTO(tache1);
		tacheService.save(tacheDTO);*/


		/*
		DetailsPhase phaseExistante = DetailsPhaseFactory.phaseDTOToPhase(phaseService.getPhaseByNom("p1")); // En supposant que "p1" est le nom de la detailsPhase

		if (phaseExistante == null) {
			DetailsPhase phase1=new DetailsPhase("p1", LocalDate.of(2024,11,3),LocalDate.of(2024,12,14),20,0,0,0,StatutPhase.A_VENIR,projetList,formuleList,tacheList);
			Tache tache1 = new Tache(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 2, 29), "tache1", "description", 10, 0, 0, 0, Statut.A_VENIR, phase1, projet, equipementList, compteList);
			TacheDTO tacheDTO = TacheFactory.tacheToTacheDTO(tache1);
			tacheService.save(tacheDTO);
		} else {
			Tache tache1 = new Tache(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 2, 29), "tache1", "description", 10, 0, 0, 0, Statut.A_VENIR, phaseExistante, projet, equipementList, compteList);
			TacheDTO tacheDTO = TacheFactory.tacheToTacheDTO(tache1);
			tacheService.save(tacheDTO);
		}

*/
//	Tache tache1 = new Tache(LocalDate.of(2024, 11, 29), LocalDate.of(2024, 11, 30), "tache1", "description", 10, 0, 0, 0, Statut.A_VENIR, detailsPhase, projet, equipementList, compteList);
//		TacheDTO tacheDTO = TacheFactory.tacheToTacheDTO(tache1);
//		tacheService.save(tacheDTO);

	}


}
