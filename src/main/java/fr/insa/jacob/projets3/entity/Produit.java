package fr.insa.jacob.projets3.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Produit")
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idGamme")
    private Gamme gamme;

    @Column(name = "reference", nullable = false)
    private String reference;

    @Column(name = "description", nullable = false)
    private String description;

    public Gamme getGamme() {
        return gamme;
    }

    public void setGamme(Gamme gamme) {
        this.gamme = gamme;
    }
}
