package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.PosteDeTravail;
import fr.insa.jacob.projets3.repository.PosteDeTravailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PosteDeTravailService {

    @Autowired
    private PosteDeTravailRepository posteDeTravailRepository;


    public void delete(Integer id) {
        posteDeTravailRepository.deleteById(id);
    }


    private PosteDeTravail requireOne(Integer id) {
        return posteDeTravailRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
