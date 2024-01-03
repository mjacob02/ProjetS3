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

    @ManyToOne
    @JoinColumn(name = "idOperation")
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "idMachine")
    private Machine machine;

    @ManyToOne
    @JoinColumn(name = "idExemplaire")
    private Exemplaire exemplaire;

    @Column(name = "debutOperation", nullable = false)
    private Double debutOperation;

    @Column(name = "finOperation", nullable = false)
    private Double finOperation;

    //getter
    public Integer getId() { return id;    }
    public void setId(Integer id) { this.id = id;    }

    public Operation getOperation() { return operation;    }
    public void setOperation(Operation operation) { this.operation = operation; }

    public Machine getMachine() { return machine;    }
    public void setMachine(Machine machine) { this.machine = machine;    }
    public Exemplaire getExemplaire() { return exemplaire;    }
    public void setExemplaire(Exemplaire exemplaire) { this.exemplaire = exemplaire; }

    public Double getDebutOperation() { return debutOperation;    }
    public void setDebutOperation(Double debutOperation) { this.debutOperation = debutOperation;  }
    public Double getFinOperation() { return finOperation;   }
    public void setFinOperation(Double finOperation) { this.finOperation = finOperation; }











}
