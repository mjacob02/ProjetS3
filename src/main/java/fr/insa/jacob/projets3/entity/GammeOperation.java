package fr.insa.jacob.projets3.entity;

import jakarta.persistence.*;


import java.io.Serializable;


@Entity
@Table(name = "Gamme_Operation")
public class GammeOperation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "idOperation")
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "idGamme")
    private Gamme gamme;

    public Operation getOperation() {
        return operation;
    }
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Gamme getGamme() {
        return gamme;
    }
    public void setGamme(Gamme gamme) {
        this.gamme = gamme;
    }


}
