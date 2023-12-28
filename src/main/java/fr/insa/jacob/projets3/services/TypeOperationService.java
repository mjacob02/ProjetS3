package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.Gamme;
import fr.insa.jacob.projets3.entity.Operation;
import fr.insa.jacob.projets3.entity.TypeOperation;
import fr.insa.jacob.projets3.repository.TypeOperationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TypeOperationService {

    @Autowired
    private TypeOperationRepository typeOperationRepository;


    public void delete(Integer id) {
        typeOperationRepository.deleteById(id);
    }

    private TypeOperation requireOne(Integer id) {
        return typeOperationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<Gamme> listAll() {
    }

    public Operation findAll(String value) {
        return null;
    }
}
