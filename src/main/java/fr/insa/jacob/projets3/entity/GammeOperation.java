package fr.insa.jacob.projets3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


import java.io.Serializable;


@Entity
@Table(name = "Gamme_Operation")
public class GammeOperation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "idOperation", nullable = false)
    private Integer idOperation;

    @Column(name = "idGamme", nullable = false)
    private Integer idGamme;

    public Integer getIdOperation() {
        return idOperation;
    }

    public Integer getIdGamme() {
        return idGamme;
    }

    public void setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
    }

    public void setIdGamme(Integer idGamme) {
        this.idGamme = idGamme;
    }
}
