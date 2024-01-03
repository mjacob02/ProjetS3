package fr.insa.jacob.projets3.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Gamme")
public class Gamme implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "reference", nullable = false)
    private String reference;

    @Column(name = "description", nullable = false)
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @ManyToMany
    @JoinTable(
            name = "GammeOperation",
            joinColumns = @JoinColumn(name = "gamme_id"),
            inverseJoinColumns = @JoinColumn(name = "operation_id"))
    Set<Operation> gammeOperation;

// TODO : handle many-tomany : https://www.baeldung.com/jpa-many-to-many
//    public Gamme getGammeOperation() {
//    }
}
