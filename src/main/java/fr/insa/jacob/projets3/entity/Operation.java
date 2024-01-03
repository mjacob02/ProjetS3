package fr.insa.jacob.projets3.entity;

import jakarta.persistence.*;


import java.io.Serializable;


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
}
