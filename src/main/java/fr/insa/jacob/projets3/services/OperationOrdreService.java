package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.OperationOrdre;
import fr.insa.jacob.projets3.repository.OperationOrdreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OperationOrdreService {

    @Autowired
    private OperationOrdreRepository operationOrdreRepository;


    public void delete(Integer id) {
        operationOrdreRepository.deleteById(id);
    }


    private OperationOrdre requireOne(Integer id) {
        return operationOrdreRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
