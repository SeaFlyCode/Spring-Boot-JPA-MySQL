package com.matheo.tp_01_tea.service;

import com.matheo.tp_01_tea.model.Produit;
import com.matheo.tp_01_tea.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    // recuperer tous les produits
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    // recuperer tous les produits avec tri
    public List<Produit> getAllProduits(String sortBy, String sortDir) {
        Sort sort;
        if (sortDir.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }
        return produitRepository.findAll(sort);
    }

    // sauvegarder un produit
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    // trouver un produit par son id
    public Optional<Produit> findById(Long id) {
        return produitRepository.findById(id);
    }

    // supprimer un produit
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }

    // rechercher par nom
    public List<Produit> searchByNom(String nom) {
        return produitRepository.findByNomContainingIgnoreCase(nom);
    }

    // filtrer par type de thé
    public List<Produit> filterByTypeThe(String typeThe) {
        return produitRepository.findByTypeThe(typeThe);
    }

    // recherche et filtre combinés
    public List<Produit> searchAndFilter(String nom, String typeThe) {
        if (nom != null && !nom.isEmpty() && typeThe != null && !typeThe.isEmpty()) {
            return produitRepository.findByNomContainingIgnoreCaseAndTypeThe(nom, typeThe);
        } else if (nom != null && !nom.isEmpty()) {
            return produitRepository.findByNomContainingIgnoreCase(nom);
        } else if (typeThe != null && !typeThe.isEmpty()) {
            return produitRepository.findByTypeThe(typeThe);
        }
        return produitRepository.findAll();
    }
}
