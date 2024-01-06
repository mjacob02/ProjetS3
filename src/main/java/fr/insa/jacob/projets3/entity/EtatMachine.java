package fr.insa.jacob.projets3.entity;

import jakarta.persistence.*;


import java.io.Serializable;


@Entity
@Table(name = "EtatMachine")
public class EtatMachine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "Reference", nullable = false)
    private String reference;

    @Column(name = "Puissance", nullable = false)
    private Double puissance;

    @Column(name = "CoutHoraire", nullable = false)
    private Double coutHoraire;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getReference() {
        return reference;
    }

    public void setReference(String  reference) {
        this. reference =  reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description ) {
        this.description = description;
    }
    public Double getPuissance() {
        return puissance;
    }
    public void setPuissance(Double puissance) {
        this.puissance = puissance;
    }

    public Double getCoutHoraire(){ return coutHoraire;}
    public void setCoutHoraire(Double coutHoraire) {
        this.coutHoraire = coutHoraire;
    }
}
