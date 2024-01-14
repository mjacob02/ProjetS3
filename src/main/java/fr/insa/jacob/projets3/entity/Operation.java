package fr.insa.jacob.projets3.entity;

import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "Operation")
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idTypeOperation")
    private TypeOperation typeOperation;

    @Column(name = "Reference", nullable = false)
    private String reference;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "operations")
    Set<Gamme> gammes;

    @ManyToMany
    @JoinTable(
            name = "Operation_Ordre",
            joinColumns = @JoinColumn(name = "idOperationAval"),
            inverseJoinColumns = @JoinColumn(name = "IdOperationAmont"))
    Set<Operation> operationsAval;

    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Gamme> getGammes() {
        return gammes;
    }

    public void setGammes(Set<Gamme> gammes) {
        this.gammes = gammes;
    }

    public Set<Operation> getOperationsAval() {
        return operationsAval;
    }

    public void setOperationsAval(Set<Operation> operationsAval) {
        this.operationsAval = operationsAval;
    }
}