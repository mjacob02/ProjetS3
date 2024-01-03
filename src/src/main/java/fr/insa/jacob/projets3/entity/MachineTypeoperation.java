package fr.insa.jacob.projets3.entity;

import fr.insa.jacob.projets3.entity.compositeKeys.MachineTypeOperationKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


import java.io.Serializable;


@Entity
@Table(name = "Machine_TypeOperation")
public class MachineTypeoperation implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private MachineTypeOperationKey id; //idMachine, idTypeOperation (composite key : both primary keys of the tables)

    @Column(name = "duree", nullable = false)
    private Double duree;

    public Double getDuree() {
        return duree;
    }

    public void setDuree(Double duree) {
        this.duree = duree;
    }


}
