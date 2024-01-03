package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.Operateur;
import fr.insa.jacob.projets3.repository.OperateurRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OperateurService {

    @Autowired
    private OperateurRepository operateurRepository;

    public void delete(Integer id) {
        operateurRepository.deleteById(id);
    }


    private Operateur requireOne(Integer id) {
        return operateurRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
