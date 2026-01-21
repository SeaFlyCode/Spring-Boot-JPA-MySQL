package com.matheo.tp_01_tea.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

/**
 * Entité représentant un produit (thé) dans la boutique
 */
@Entity
@Table(name = "produits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères")
    @Column(nullable = false, length = 100)
    private String nom;

    // type de thé (Vert, Noir, Oolong, Blanc, Pu-erh)
    @Column(length = 50)
    private String typeThe;

    // origine du thé
    @Column(length = 50)
    private String origine;

    @NotNull(message = "Le prix est obligatoire")
    @Min(value = 5, message = "Le prix doit etre supérieur à 5€")
    @Max(value = 100, message = "Le prix maximum est de 100€")
    @Column(nullable = false)
    private Float prix;

    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 0, message = "La quantité ne peut pas etre négative")
    @Column(nullable = false)
    private Integer quantiteStock;

    @Size(max = 500, message = "La description est trop longue")
    @Column(length = 500)
    private String description;

    private LocalDate dateReception;
}
