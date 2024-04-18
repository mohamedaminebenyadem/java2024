package services;

import models.Type;
import connectionDB.DataBaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeService implements CrudInterface<Type> {

    private Connection cnx;

    public TypeService() {
        cnx = DataBaseConnector.getInstance().getCnx();
    }

    @Override
    public void create(Type type) {
        String query = "INSERT INTO Type (type) VALUES (?)";
        try (PreparedStatement pst = cnx.prepareStatement(query)) {
            pst.setString(1, type.getType());

            pst.executeUpdate();
            System.out.println("Type ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'ajout du type : " + ex.getMessage());
        }
    }

    @Override
    public List<Type> getAll() {
        List<Type> types = new ArrayList<>();
        String query = "SELECT * FROM Type";
        try (PreparedStatement pst = cnx.prepareStatement(query)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Type type = new Type();
                    type.setId(rs.getLong("id"));
                    type.setType(rs.getString("type"));

                    types.add(type);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la récupération des types : " + ex.getMessage());
        }
        return types;
    }

    @Override
    public Type getById(int id) {
        String query = "SELECT * FROM Type WHERE id=?";
        try (PreparedStatement pst = cnx.prepareStatement(query)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Type type = new Type();
                    type.setId(rs.getLong("id"));
                    type.setType(rs.getString("type"));

                    return type;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la récupération du type : " + ex.getMessage());
        }
        return null;
    }

    @Override
    public void update(Type type) {
        if (type != null) {
            String query = "UPDATE Type SET type=? WHERE id=?";
            try (PreparedStatement pst = cnx.prepareStatement(query)) {
                pst.setString(1, type.getType());
                pst.setLong(2, type.getId());

                int rowsUpdated = pst.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Type mis à jour avec succès");
                } else {
                    System.out.println("Aucun type trouvé avec l'ID : " + type.getId());
                }
            } catch (SQLException ex) {
                System.err.println("Erreur lors de la mise à jour du type : " + ex.getMessage());
            }
        } else {
            System.err.println("L'objet type est null.");
        }
    }

    @Override
    public void delete(int id) {
        if (id > 0) {
            String query = "DELETE FROM Type WHERE id=?";
            try (PreparedStatement pst = cnx.prepareStatement(query)) {
                pst.setInt(1, id);
                int rowsDeleted = pst.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Type supprimé avec succès");
                } else {
                    System.out.println("Aucun type trouvé avec l'ID spécifié.");
                }
            } catch (SQLException ex) {
                System.err.println("Erreur lors de la suppression du type : " + ex.getMessage());
            }
        } else {
            System.err.println("L'ID du type à supprimer n'est pas valide.");
        }
    }
}
