
package models;


public class Avis {
    private Long id;
    private String rating;
    private String commentaire;
    private String titre;

    // Getters
    public Long getId() {
        return id;
    }

    public String getRating() {
        return rating;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getTitre() {
        return titre;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    @Override
    public String toString() {
        return "Avis{" +
                "id=" + id +
                ", rating='" + rating + '\'' +
                ", commentaire='" + commentaire + '\'' +
                ", titre='" + titre + '\'' +
                '}';
    }
}
