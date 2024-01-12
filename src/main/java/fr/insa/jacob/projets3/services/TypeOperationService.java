package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.*;
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


    public void delete(TypeOperation typeOperation) {
        typeOperationRepository.delete(typeOperation);
    }

    private TypeOperation requireOne(Integer id) {
        return typeOperationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<TypeOperation> listAll() {
        return typeOperationRepository.findAll();
    }

    public List<TypeOperation> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return typeOperationRepository.findAll();
        } else {
            return typeOperationRepository.search(stringFilter);
        }
    }


    public TypeOperation save(TypeOperation typeOperation) {
        if (typeOperation == null) {
            System.err.println("TypeOperation is null. Are you sure you have connected your form to the application?");
            return null;
        }
        return typeOperationRepository.save(typeOperation);
    }


}
