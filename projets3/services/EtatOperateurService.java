package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.EtatOperateur;
import fr.insa.jacob.projets3.repository.EtatOperateurRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EtatOperateurService {

    @Autowired
    private EtatOperateurRepository etatOperateurRepository;


    public void delete(Integer id) {
        etatOperateurRepository.deleteById(id);
    }

    private EtatOperateur requireOne(Integer id) {
        return etatOperateurRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
