package com.matheo.tp_01_tea.controller;

import com.matheo.tp_01_tea.model.Produit;
import com.matheo.tp_01_tea.service.ProduitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    // listes pour les menus deroulants
    private final List<String> typesThe = Arrays.asList("Vert", "Noir", "Oolong", "Blanc", "Pu-erh");
    private final List<String> origines = Arrays.asList("Chine", "Japon", "Inde", "Sri Lanka", "Taiwan");

    // page d'accueil - liste des produits
    @GetMapping("/")
    public String index(
            @RequestParam(required = false) String recherche,
            @RequestParam(required = false) String typeThe,
            @RequestParam(defaultValue = "nom") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {

        List<Produit> produits;

        // recherche et filtrage
        if ((recherche != null && !recherche.isEmpty()) || (typeThe != null && !typeThe.isEmpty())) {
            produits = produitService.searchAndFilter(recherche, typeThe);
        } else {
            produits = produitService.getAllProduits(sortBy, sortDir);
        }

        model.addAttribute("produits", produits);
        model.addAttribute("typesThe", typesThe);
        model.addAttribute("recherche", recherche);
        model.addAttribute("typeTheFiltre", typeThe);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "index";
    }

    // formulaire nouveau produit
    @GetMapping("/nouveau")
    public String nouveauProduit(Model model) {
        model.addAttribute("produit", new Produit());
        model.addAttribute("typesThe", typesThe);
        model.addAttribute("origines", origines);
        return "formulaire-produit";
    }

    // enregistrer le produit
    @PostMapping("/enregistrer")
    public String enregistrerProduit(@Valid @ModelAttribute Produit produit,
                                      BindingResult result,
                                      Model model) {
        if (result.hasErrors()) {
            model.addAttribute("typesThe", typesThe);
            model.addAttribute("origines", origines);
            return "formulaire-produit";
        }
        produitService.saveProduit(produit);
        return "redirect:/";
    }

    // formulaire modification
    @GetMapping("/modifier/{id}")
    public String modifierProduit(@PathVariable Long id, Model model) {
        Optional<Produit> produit = produitService.findById(id);
        if (produit.isPresent()) {
            model.addAttribute("produit", produit.get());
            model.addAttribute("typesThe", typesThe);
            model.addAttribute("origines", origines);
            return "formulaire-produit";
        }
        return "redirect:/";
    }

    // mettre a jour le produit
    @PostMapping("/modifier/{id}")
    public String mettreAJourProduit(@PathVariable Long id,
                                      @Valid @ModelAttribute Produit produit,
                                      BindingResult result,
                                      Model model) {
        if (result.hasErrors()) {
            model.addAttribute("typesThe", typesThe);
            model.addAttribute("origines", origines);
            return "formulaire-produit";
        }
        produit.setId(id);
        produitService.saveProduit(produit);
        return "redirect:/";
    }

    // supprimer produit
    @GetMapping("/supprimer/{id}")
    public String supprimerProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return "redirect:/";
    }
}
