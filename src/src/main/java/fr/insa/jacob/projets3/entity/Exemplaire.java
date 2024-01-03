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

    @ManyToOne
    @JoinColumn(name = "idProduit")
    private Produit produit;

    @Column(name = "numeroDeSerie", nullable = false)
    private Integer numeroDeSerie;

    public Integer getId() {
        return id;
    }

    public Integer getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setNumeroDeSerie(Integer numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
