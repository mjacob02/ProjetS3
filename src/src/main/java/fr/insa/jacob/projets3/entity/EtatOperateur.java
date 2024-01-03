package fr.insa.jacob.projets3.entity;

import jakarta.persistence.*;


import java.io.Serializable;


@Entity
@Table(name = "EtatOperateur")
public class EtatOperateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom", nullable = false)
    private String nom;

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
