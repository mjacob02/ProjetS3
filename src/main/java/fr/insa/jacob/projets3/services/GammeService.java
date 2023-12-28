package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.Gamme;
import fr.insa.jacob.projets3.repository.GammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GammeService {

    @Autowired
    private GammeRepository gammeRepository;
    private Gamme requireOne(Integer id) {
        return gammeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    /***
     * Save Gamme
     */
    public Gamme save(Gamme gamme) {
        if (gamme == null) {
            System.err.println("Gamme is null. Are you sure you have connected your form to the application?");
            return null;
        }
        return gammeRepository.save(gamme);
    }

    /***
     * List all Gamme
     */
    public List<Gamme> listAll() {
        return gammeRepository.findAll();
    }

    public void delete(Gamme gamme) {
    }

    public Gamme findAll(String value) {
    }
}
