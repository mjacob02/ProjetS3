package fr.insa.jacob.projets3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


import java.io.Serializable;


@Entity
@Table(name = "Machine_TypeOperation")
public class MachineTypeoperation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "idMachine", nullable = false)
    private Integer idMachine;

    @Column(name = "idTypeOperation", nullable = false)
    private Integer idTypeOperation;

    @Column(name = "duree", nullable = false)
    private Double duree;

    public Integer getIdMachine() {
        return idMachine;
    }

    public Integer getIdTypeOperation() {
        return idTypeOperation;
    }

    public Double getDuree() {
        return duree;
    }

    public void setIdMachine(Integer idMachine) {
        this.idMachine = idMachine;
    }

    public void setIdTypeOperation(Integer idTypeOperation) {
        this.idTypeOperation = idTypeOperation;
    }

    public void setDuree(Double duree) {
        this.duree = duree;
    }


}
