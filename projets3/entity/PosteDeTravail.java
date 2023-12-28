package fr.insa.jacob.projets3.entity;

import jakarta.persistence.*;


import java.io.Serializable;


@Entity
@Table(name = "PosteDeTravail")
public class PosteDeTravail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "positionX", nullable = false)
    private Integer positionX;

    @Column(name = "positionY", nullable = false)
    private Integer positionY;

    @Column(name = "description", nullable = false)
    private String description;

}
