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

    @Column(name = "idTypeOperation", nullable = false)
    private Integer idTypeOperation;

    // TODO : ajouter une colonne "reference" dans la table "Operation" dans la base de données (pour avoir un nom lisible pour l'opération : sinon il n'y a pas de nom !)
    // TODO : ajouter ensuite la colonne "reference" dans cette classe Operation (comme pour les autres colonnes de la table Operation)
    // TODO : ajouter les getter et setter pour la colonne "reference" (comme pour les autres colonnes de la table Operation)
    // TODO : faire les mêmes étapes pour "description"
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
}
