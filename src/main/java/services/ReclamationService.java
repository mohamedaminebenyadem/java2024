package services;

import models.Reclamation;
import connectionDB.DataBaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReclamationService implements CrudInterface<Reclamation> {

    private Connection cnx;

    public ReclamationService() {
        cnx = DataBaseConnector.getInstance().getCnx();
    }

    @Override
    public void create(Reclamation reclamation) {
        String query = "INSERT INTO Reclamation (nom, prenom, email, tel, etat, description, date_reclamation, type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = cnx.prepareStatement(query)) {
            pst.setString(1, reclamation.getNom());
            pst.setString(2, reclamation.getPrenom());
            pst.setString(3, reclamation.getEmail());
            pst.setInt(4, reclamation.getTel());
            pst.setString(5, reclamation.getEtat());
            pst.setString(6, reclamation.getDescription());
            pst.setDate(7, java.sql.Date.valueOf(reclamation.getDateReclamation()));
            pst.setLong(8, reclamation.getType().getId()); // Set the type_id parameter

            pst.executeUpdate();
            System.out.println("Réclamation ajoutée avec succès");
        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'ajout de la réclamation : " + ex.getMessage());
        }
    }

    @Override
    public void update(Reclamation reclamation) {
    //a changer
        if (reclamation == null) {
            System.err.println("L'objet reclamation est null.");
            return;
        }

        String query = "UPDATE Reclamation SET nom=?, prenom=?, email=?, tel=?, etat=?, description=?, date_reclamation=? WHERE id=?";
        try (PreparedStatement pst = cnx.prepareStatement(query)) {
            pst.setString(1, reclamation.getNom());
            pst.setString(2, reclamation.getPrenom());
            pst.setString(3, reclamation.getEmail());
            pst.setInt(4, reclamation.getTel());
            pst.setString(5, reclamation.getEtat());
            pst.setString(6, reclamation.getDescription());
            pst.setDate(7, java.sql.Date.valueOf(reclamation.getDateReclamation()));
            pst.setLong(8, reclamation.getId());

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Réclamation mise à jour avec succès");
            } else {
                System.out.println("Aucune réclamation trouvée avec l'ID : " + reclamation.getId());
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la mise à jour de la réclamation : " + ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Reclamation WHERE id=?";
        try (PreparedStatement pst = cnx.prepareStatement(query)) {
            pst.setInt(1, id);

            pst.executeUpdate();
            System.out.println("Réclamation supprimée avec succès");
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la suppression de la réclamation : " + ex.getMessage());
        }
    }
//stream
    @Override
    public Reclamation getById(int id) {
        String query = "SELECT * FROM Reclamation WHERE id=?";
        try (PreparedStatement pst = cnx.prepareStatement(query)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Reclamation reclamation = new Reclamation();
                    reclamation.setId(rs.getLong("id"));
                    reclamation.setNom(rs.getString("nom"));
                    reclamation.setPrenom(rs.getString("prenom"));
                    reclamation.setEmail(rs.getString("email"));
                    reclamation.setTel(rs.getInt("tel"));
                    reclamation.setEtat(rs.getString("etat"));
                    reclamation.setDescription(rs.getString("description"));
                    reclamation.setDateReclamation(rs.getDate("date_reclamation").toLocalDate());
                    // Assuming there's a method to fetch Type by ID
                    // reclamation.setType(typeService.getTypeById(rs.getLong("type_id")));

                    return reclamation;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la récupération de la réclamation : " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Reclamation> getAll() {
        List<Reclamation> reclamations = new ArrayList<>();
        String query = "SELECT * FROM Reclamation";
        try (PreparedStatement pst = cnx.prepareStatement(query)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Reclamation reclamation = new Reclamation();
                    reclamation.setId(rs.getLong("id"));
                    reclamation.setNom(rs.getString("nom"));
                    reclamation.setPrenom(rs.getString("prenom"));
                    reclamation.setEmail(rs.getString("email"));
                    reclamation.setTel(rs.getInt("tel"));
                    reclamation.setEtat(rs.getString("etat"));
                    reclamation.setDescription(rs.getString("description"));
                    reclamation.setDateReclamation(rs.getDate("date_reclamation").toLocalDate());
                    // Assuming there's a method to fetch Type by ID
                    // reclamation.setType(typeService.getTypeById(rs.getLong("type_id")));

                    reclamations.add(reclamation);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la récupération des réclamations : " + ex.getMessage());
        }
        return reclamations;
    }

    public void displayPostTypesOfReclamationsUsed() {
        String query = "SELECT DISTINCT Type.type FROM Reclamation INNER JOIN Type ON Reclamation.type_id = Type.id";

        try (PreparedStatement pst = cnx.prepareStatement(query)) {
            try (ResultSet rs = pst.executeQuery()) {
                System.out.println("Post Types of Reclamations Used:");
                while (rs.next()) {
                    String postType = rs.getString("type");
                    System.out.println(postType);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error displaying post types of reclamations used: " + ex.getMessage());
        }
    }
    public List<Reclamation> getAllSortedByDate() {
        List<Reclamation> reclamations = new ArrayList<>();
        String query = "SELECT * FROM Reclamation ORDER BY date_reclamation ASC"; // Sorting by date_reclamation in ascending order
        try (PreparedStatement pst = cnx.prepareStatement(query)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Reclamation reclamation = new Reclamation();
                    reclamation.setId(rs.getLong("id"));
                    reclamation.setNom(rs.getString("nom"));
                    reclamation.setPrenom(rs.getString("prenom"));
                    reclamation.setEmail(rs.getString("email"));
                    reclamation.setTel(rs.getInt("tel"));
                    reclamation.setEtat(rs.getString("etat"));
                    reclamation.setDescription(rs.getString("description"));
                    reclamation.setDateReclamation(rs.getDate("date_reclamation").toLocalDate());
                    // Assuming there's a method to fetch Type by ID
                    // reclamation.setType(typeService.getTypeById(rs.getLong("type_id")));

                    reclamations.add(reclamation);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la récupération des réclamations triées par date : " + ex.getMessage());
        }
        return reclamations;
    }
    public List<Reclamation> searchByNomOrPrenom(String searchTerm) {
        List<Reclamation> reclamations = new ArrayList<>();
        String query = "SELECT * FROM Reclamation WHERE nom LIKE ? OR prenom LIKE ?";
        try (PreparedStatement pst = cnx.prepareStatement(query)) {
            // Setting the search term for both nom and prenom fields using wildcards
            pst.setString(1, "%" + searchTerm + "%");
            pst.setString(2, "%" + searchTerm + "%");

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Reclamation reclamation = new Reclamation();
                    reclamation.setId(rs.getLong("id"));
                    reclamation.setNom(rs.getString("nom"));
                    reclamation.setPrenom(rs.getString("prenom"));
                    reclamation.setEmail(rs.getString("email"));
                    reclamation.setTel(rs.getInt("tel"));
                    reclamation.setEtat(rs.getString("etat"));
                    reclamation.setDescription(rs.getString("description"));
                    reclamation.setDateReclamation(rs.getDate("date_reclamation").toLocalDate());
                    // Assuming there's a method to fetch Type by ID
                    // reclamation.setType(typeService.getTypeById(rs.getLong("type_id")));

                    reclamations.add(reclamation);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la recherche des réclamations : " + ex.getMessage());
        }
        return reclamations;
    }


}
