package fr.insa.jacob.projets3.entity;

import jakarta.persistence.*;


import java.io.Serializable;


@Entity
@Table(name = "OperationEffectuee")
public class OperationEffectuee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "idOperation", nullable = false)
    private Integer idOperation;

    @Column(name = "idMachine", nullable = false)
    private Integer idMachine;

    @Column(name = "idExemplaire", nullable = false)
    private Integer idExemplaire;

    @Column(name = "debutOperation", nullable = false)
    private Double debutOperation;

    @Column(name = "finOperation", nullable = false)
    private Double finOperation;

}
