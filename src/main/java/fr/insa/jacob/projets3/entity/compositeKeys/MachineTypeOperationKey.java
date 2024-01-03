package fr.insa.jacob.projets3.entity.compositeKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * The primary key class for the Machine_TypeOperation database table.
 * Using https://www.baeldung.com/jpa-many-to-many#many-to-many-using-a-composite-key as a guide
 */
@Embeddable
public class MachineTypeOperationKey implements Serializable {
    @Column(name = "idMachine", nullable = false)
    private Integer idMachine;

    @Column(name = "idTypeOperation", nullable = false)
    private Integer idTypeOperation;

    public Integer getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(Integer idMachine) {
        this.idMachine = idMachine;
    }

    public Integer getIdTypeOperation() {
        return idTypeOperation;
    }

    public void setIdTypeOperation(Integer idTypeOperation) {
        this.idTypeOperation = idTypeOperation;
    }
}
