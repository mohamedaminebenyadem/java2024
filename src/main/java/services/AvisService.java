package services;


import models.Avis;
import connectionDB.DataBaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvisService implements CrudInterface<Avis> {

    private Connection cnx;

    public AvisService() {
        cnx = DataBaseConnector.getInstance().getCnx();
    }

    @Override
    public void create(Avis avis) {
        String query = "INSERT INTO Avis (rating, commentaire, titre) VALUES (?, ?, ?)";
        try (PreparedStatement pst = cnx.prepareStatement(query)) {
            pst.setString(1, avis.getRating());
            pst.setString(2, avis.getCommentaire());
            pst.setString(3, avis.getTitre());

            pst.executeUpdate();
            System.out.println("Avis ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'ajout de l'avis : " + ex.getMessage());
        }
    }

    @Override
    public void update(Avis avis) {
        if (avis != null) {
            String query = "UPDATE Avis SET rating=?, commentaire=?, titre=? WHERE id=?";
            try (PreparedStatement pst = cnx.prepareStatement(query)) {
                pst.setString(1, avis.getRating());
                pst.setString(2, avis.getCommentaire());
                pst.setString(3, avis.getTitre());
                pst.setLong(4, avis.getId());

                int rowsUpdated = pst.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Avis mis à jour avec succès");
                } else {
                    System.out.println("Aucun avis trouvé avec l'ID : " + avis.getId());
                }
            } catch (SQLException ex) {
                System.err.println("Erreur lors de la mise à jour de l'avis : " + ex.getMessage());
            }
        } else {
            System.err.println("L'objet avis est null.");
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Avis WHERE id=?";
        try (PreparedStatement pst = cnx.prepareStatement(query)) {
            pst.setInt(1, id);

            pst.executeUpdate();
            System.out.println("Avis supprimé avec succès");
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la suppression de l'avis : " + ex.getMessage());
        }
    }
//stream
    @Override
    public Avis getById(int id) {
        String query = "SELECT * FROM Avis WHERE id=?";
        try (PreparedStatement pst = cnx.prepareStatement(query)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Avis avis = new Avis();
                    avis.setId(rs.getLong("id"));
                    avis.setRating(rs.getString("rating"));
                    avis.setCommentaire(rs.getString("commentaire"));
                    avis.setTitre(rs.getString("titre"));

                    return avis;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la récupération de l'avis : " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Avis> getAll() {
        List<Avis> avisList = new ArrayList<>();
        String query = "SELECT * FROM Avis";
        try (PreparedStatement pst = cnx.prepareStatement(query)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Avis avis = new Avis();
                    avis.setId(rs.getLong("id"));
                    avis.setRating(rs.getString("rating"));
                    avis.setCommentaire(rs.getString("commentaire"));
                    avis.setTitre(rs.getString("titre"));

                    avisList.add(avis);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la récupération des avis : " + ex.getMessage());
        }
        return avisList;
    }
    public String getMostRatedAvisTitre() {
        String query = "SELECT titre FROM Avis ORDER BY rating DESC LIMIT 1";
        try (PreparedStatement pst = cnx.prepareStatement(query)) {
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("titre");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error retrieving the title of the most rated avis: " + ex.getMessage());
        }
        return null;
    }
}