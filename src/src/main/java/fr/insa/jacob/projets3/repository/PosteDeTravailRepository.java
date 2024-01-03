package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.PosteDeTravail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PosteDeTravailRepository extends JpaRepository<PosteDeTravail, Integer>, JpaSpecificationExecutor<PosteDeTravail> {

}