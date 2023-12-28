package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.Exemplaire;
import fr.insa.jacob.projets3.repository.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ExemplaireService {

    @Autowired
    private ExemplaireRepository exemplaireRepository;


    public void delete(Integer id) {
        exemplaireRepository.deleteById(id);
    }
    private Exemplaire requireOne(Integer id) {
        return exemplaireRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<Exemplaire> findAll(String stringFilter ) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return exemplaireRepository.findAll();
        } else {
            return exemplaireRepository.search(stringFilter);
        }
    }

    public void save(Exemplaire exemplaire) {
    }
}
