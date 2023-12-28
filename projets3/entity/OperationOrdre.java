package fr.insa.jacob.projets3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import java.io.Serializable;


@Entity
@Table(name = "Operation_Ordre")
public class OperationOrdre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idOperationAmont", nullable = false)
    private Integer idOperationAmont;

    @Column(name = "idOperationAval", nullable = false)
    private Integer idOperationAval;

}
