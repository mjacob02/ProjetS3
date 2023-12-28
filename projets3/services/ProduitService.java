package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.Produit;
import fr.insa.jacob.projets3.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    private Produit requireOne(Integer id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    /***
     * Save Produit
     */
    public Produit save(Produit produit) {
        if (produit == null) {
            System.err.println("Produit is null. Are you sure you have connected your form to the application?");
            return null;
        }
        return produitRepository.save(produit);
    }

    /***
     * Delete Produit
     */
    public void delete(Produit produit) {
        produitRepository.delete(produit);
    }

    /***
     * List all Produit
     */
    public List<Produit> listAll() {
        return produitRepository.findAll();
    }

    /***
     * Find all Produit with search filter on reference
     */
    public List<Produit> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return produitRepository.findAll();
        } else {
            return produitRepository.search(stringFilter);
        }
    }



    /*    *//***
     * Update Produit
     *//*
    public Produit update(Integer id, Produit produit) {
        Produit existingProduit = requireOne(id);
        BeanUtils.copyProperties(produit, existingProduit);
        return produitRepository.save(existingProduit);
    }

    *//***
     * Default Produit
     *//*
    public Produit getDefault() {
        Produit produit = new Produit();
        produit.setReference("Default");
        produit.setDescription("Default");
        return produit;
    }*/
}
