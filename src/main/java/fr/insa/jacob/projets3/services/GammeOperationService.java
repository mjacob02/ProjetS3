package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.Gamme;
import fr.insa.jacob.projets3.entity.GammeOperation;
import fr.insa.jacob.projets3.entity.Produit;
import fr.insa.jacob.projets3.repository.GammeOperationRepository;
import fr.insa.jacob.projets3.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GammeOperationService {
    @Autowired
    private GammeOperationRepository gammeOperationRepository;

    // TODO : add save, delete, listAll
    // No findAll : GammeOperation is a liaison table


}
