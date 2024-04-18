package models;

import java.time.LocalDate;

public class Reclamation {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private Integer tel;
    private String etat = "en cours de traitement";
    private String description;
    private LocalDate dateReclamation;
    private Type type;


    public Reclamation( String nom, String prenom, String email, Integer tel, String description, LocalDate dateReclamation, Type type) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.description = description;
        this.dateReclamation = dateReclamation;
        this.type = type;
    }

    public Reclamation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateReclamation() {
        return dateReclamation;
    }

    public void setDateReclamation(LocalDate dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", tel=" + tel +
                ", etat='" + etat + '\'' +
                ", description='" + description + '\'' +
                ", dateReclamation=" + dateReclamation +
                ", type=" + type +
                '}';
    }}