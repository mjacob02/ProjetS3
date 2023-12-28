package fr.insa.jacob.projets3.entity;

import jakarta.persistence.*;


import java.io.Serializable;


@Entity
@Table(name = "Exemplaire")
public class Exemplaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "idProduit", nullable = false)
    private Integer idProduit;

    @Column(name = "reference", nullable = false)
    private String reference;

    @Column(name = "numeroDeSerie", nullable = false)
    private Integer numeroDeSerie;

    public Integer getId() {
        return id;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public String getReference() {
        return reference;
    }

    public Integer getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setNumeroDeSerie(Integer numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }
}
