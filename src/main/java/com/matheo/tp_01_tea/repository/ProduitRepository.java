package com.matheo.tp_01_tea.repository;

import com.matheo.tp_01_tea.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    // recherche par nom
    List<Produit> findByNomContainingIgnoreCase(String nom);

    // filtre par type
    List<Produit> findByTypeThe(String typeThe);

    // recherche par nom et type
    List<Produit> findByNomContainingIgnoreCaseAndTypeThe(String nom, String typeThe);
}
