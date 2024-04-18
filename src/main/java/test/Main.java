package test;
import services.AvisService;
import services.TypeService;
import models.Avis;
import models.Reclamation;
import models.Type;
import java.time.LocalDate;
import java.util.List;

import services.ReclamationService;
public class Main {
    public static void main(String[] args) {


        TypeService typeService = new TypeService();
        ReclamationService  reclamationService= new ReclamationService  ();


        Type type = typeService.getById(1);

        Reclamation reclamationToAdd = new Reclamation(
                "John",
                "Doe",
                "aa4.doe@example.com",
                123456789,
                "Description de la réclamation",
                LocalDate.now(),
                type
        );

        // Ajouter la réclamation
//        reclamationService.create(reclamationToAdd);

        // Récupérer toutes les réclamations et les afficher
//        List<Reclamation> reclamations = reclamationService.getAll();
//        System.out.println("\nListe des réclamations après l'ajout : ");
//        for (Reclamation reclamation : reclamations) {
//            System.out.println(reclamation);
//        }

        /* Update */
     //   Reclamation reclamationToUpdate = reclamationService.getById(1); // Supposez que vous avez déjà une réclamation avec l'ID 2

//        if (reclamationToUpdate != null) {
//            // Mettre à jour les champs nécessaires de la réclamation
//            reclamationToUpdate.setNom(" amine");
//            reclamationToUpdate.setDescription("aaa description");
//
//            // Appeler la méthode de mise à jour de la réclamation
//            reclamationService.update(reclamationToUpdate);
//        } else {
//            System.out.println("Aucune réclamation trouvée pour la mise à jour.");
//        }

//delete
//reclamationService.delete(1);

/**************************************************************** type test ******************************************************************************************/




     //  Type type = new Type();
        type.setType("Nouveau type");

        // Ajout du nouveau type
      //  typeService.create(type);

        // Affichage de tous les types avant la mise à jour
//    System.out.println("Liste des types avant la mise à jour : ");
//    List<Type> typesBeforeUpdate = typeService.getAll();
//    for (Type t : typesBeforeUpdate) {
//        System.out.println(t);
//    }

        // Sélection d'un type existant pour la mise à jour


        // Modification du type sélectionné
//         Type typeToUpdate = typeService.getById(3);
//    if (typeToUpdate != null) {
//        typeToUpdate.setType("Type modifié");
//        // Mise à jour du type dans la base de données
//        typeService.update(typeToUpdate);
//    } else {
//        System.out.println("Aucun type trouvé pour la mise à jour.");
//    }

        // Affichage de tous les types après la mise à jour
//    System.out.println("\nListe des types après la mise à jour : ");
//    List<Type> typesAfterUpdate = typeService.getAll();
//    for (Type t : typesAfterUpdate) {
//        System.out.println(t);
//    }

        // Suppression d'un type
//    long typeIdToDelete = 20;
//   typeService.delete((int) typeIdToDelete);

//    // Affichage de tous les types après la suppression
//    System.out.println("\nListe des types après la suppression : ");
//    List<Type> typesAfterDeletion = typeService.getAll();
//    for (Type t : typesAfterDeletion) {
//        System.out.println(t);
//    }
/**************************************************************** avis test ******************************************************************************************/

        AvisService avisService = new AvisService();

        // Création d'un nouvel avis
        Avis nouvelAvis = new Avis();
        nouvelAvis.setRating("5");
        nouvelAvis.setCommentaire("Excellent service !");
        nouvelAvis.setTitre("Super expérience");

        // Ajout de l'avis
        // avisService.create(nouvelAvis);

//        // Affichage de tous les avis après l'ajout


//Avis avisToUpdate = avisService.getById(1); // Remplacez 1 par l'ID de l'avis que vous souhaitez mettre à jour
//
//// Vérifier si l'avis existe
//if (avisToUpdate != null) {
//    // Mettre à jour les champs nécessaires de l'avis
//    avisToUpdate.setCommentaire("Très satisfait !");
//
//    // Appeler la méthode de mise à jour de l'avis
//    avisService.update(avisToUpdate);
//    System.out.println("Avis mis à jour avec succès : " + avisToUpdate);
//} else {
//    System.out.println("Aucun avis trouvé avec l'ID spécifié.");
//}
//        // Affichage de tous les avis

// Récupérer la liste des avis après la modification
// List<Avis> avisList;
//avisList = avisService.getAll();
//for (Avis avis : avisList) {
//    System.out.println(avis);
//}
        // Suppression de l'avis ajouté

//        long nouvelAvisId = 10;
//        Avis avisToDelete = avisService.getById((int) nouvelAvisId);
//        if (avisToDelete != null) {
//avisService.delete(Math.toIntExact(avisToDelete.getId()));
//            System.out.println("L'avis a été supprimé avec succès.");
//        } else {
//            System.out.println("Aucun avis trouvé avec l'ID : " + nouvelAvisId);
//        }



///metier 1
   // reclamationService.displayPostTypesOfReclamationsUsed();



        ///metier2

//        String mostRatedAvisTitre = avisService.getMostRatedAvisTitre();
//        if (mostRatedAvisTitre != null) {
//            System.out.println("Title of the most rated avis: " + mostRatedAvisTitre);
//        } else {
//            System.out.println("No avis found.");
//        }


        ///metier 3
//        List<Reclamation> sortedReclamations = reclamationService.getAllSortedByDate();
//
//        // Display the sorted reclamations
//        System.out.println("Reclamations sorted by date:");
//        for (Reclamation reclamation : sortedReclamations) {
//            System.out.println(reclamation);
//
//        }
        ///metier 4

//        String searchTerm = "hanta";
//        List<Reclamation> searchedReclamations = reclamationService.searchByNomOrPrenom(searchTerm);
//
//        System.out.println("Reclamations matching the search term:");
//        for (Reclamation reclamation : searchedReclamations) {
//            System.out.println(reclamation);
//        }

    }}
