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

    //getter
    public Integer getId() { return id;    }
    public void setId(Integer id) { this.id = id;    }

    public Operation getOperation() { return Operation;    }
    public void setOperation(Integer Operation) { this.Operation = Operation; }
    public Machine getMachine() { return Machine;    }
    public void setMachine(Integer Machine) { this.Machine = Machine;    }
    public Exemplaire getExemplaire() { return Exemplaire;    }
    public void setExemplaire(Integer Exemplaire) { this.Exemplaire = Exemplaire; }

    public Double getDebutOperation() { return debutOperation;    }
    public void setDebutOperation(Double debutOperation) { this.debutOperation = debutOperation;  }
    public Double getFinOperation() { return finOperation;   }
    public void setFinOperation(Double finOperation) { this.finOperation = finOperation; }











}
