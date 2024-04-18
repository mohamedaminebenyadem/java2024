package models;

import java.util.ArrayList;
import java.util.List;


public class Type {
    private Long id;
    private String type;
    private List<Reclamation> reclamations = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Reclamation> getReclamations() {
        return reclamations;
    }

    // Constructeurs, getters et setters
    public void setReclamations(List<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}