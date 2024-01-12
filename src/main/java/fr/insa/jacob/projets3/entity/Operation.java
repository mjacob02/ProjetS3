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

    @Column(name = "idTypeOperation", nullable = false)
    private Integer idTypeOperation;

    @Column(name = "Reference", nullable = false)
    private String reference;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "operations")
    Set<Gamme> gammes;
    public Integer getId() {
        return id;
    }

    public Integer getIdTypeOperation() {
        return idTypeOperation;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdTypeOperation(Integer idTypeOperation) {
        this.idTypeOperation = idTypeOperation;
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

}