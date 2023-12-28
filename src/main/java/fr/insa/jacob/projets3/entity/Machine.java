package fr.insa.jacob.projets3.entity;

import jakarta.persistence.*;


import java.io.Serializable;


@Entity
@Table(name = "Machine")
public class Machine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "idPosteDeTravail", nullable = false)
    private Integer idPosteDeTravail;

    @Column(name = "idEtatMachine", nullable = false)
    private Integer idEtatMachine;

    @Column(name = "reference", nullable = false)
    private String reference;

    @Column(name = "puissance", nullable = false)
    private Double puissance;

    @Column(name = "coutHoraire", nullable = false)
    private Double coutHoraire;

    @Column(name = "description", nullable = false)
    private String description;

    public Integer getId() {
        return id;
    }

    public Integer getIdPosteDeTravail() {
        return idPosteDeTravail;
    }

    public Integer getIdEtatMachine() {
        return idEtatMachine;
    }

    public String getReference() {
        return reference;
    }

    public Double getPuissance() {
        return puissance;
    }

    public Double getCoutHoraire() {
        return coutHoraire;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdPosteDeTravail(Integer idPosteDeTravail) {
        this.idPosteDeTravail = idPosteDeTravail;
    }

    public void setIdEtatMachine(Integer idEtatMachine) {
        this.idEtatMachine = idEtatMachine;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setPuissance(Double puissance) {
        this.puissance = puissance;
    }

    public void setCoutHoraire(Double coutHoraire) {
        this.coutHoraire = coutHoraire;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
