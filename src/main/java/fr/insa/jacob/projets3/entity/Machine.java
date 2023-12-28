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

}
