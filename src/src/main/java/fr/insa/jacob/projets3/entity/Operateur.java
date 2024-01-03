package fr.insa.jacob.projets3.entity;

import jakarta.persistence.*;


import java.io.Serializable;


@Entity
@Table(name = "Operateur")
public class Operateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "idEtatOperateur", nullable = false)
    private Integer idEtatOperateur;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    public Integer getId() {
        return id;
    }

    public Integer getIdEtatOperateur() {
        return idEtatOperateur;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdEtatOperateur(Integer idEtatOperateur) {
        this.idEtatOperateur = idEtatOperateur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
