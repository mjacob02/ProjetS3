package fr.insa.jacob.projets3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


import java.io.Serializable;


@Entity
@Table(name = "Operateur_PosteDeTravail_Affectation")
public class OperateurPostedetravailAffectation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "idOperateur", nullable = false)
    private Integer idOperateur;

    @Column(name = "idPosteDeTravail", nullable = false)
    private Integer idPosteDeTravail;

}
