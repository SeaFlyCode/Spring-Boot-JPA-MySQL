# Boutique de Thés

Application Spring Boot pour gérer une boutique de thés.

## Fonctionnalités

- Ajout, modification et suppression de produits (thés)
- Recherche par nom
- Filtre par type de thé
- Tri par nom, prix ou stock

## Technologies

- Spring Boot
- Spring Data JPA
- MySQL
- Thymeleaf
- Bootstrap 5

## Configuration

Modifier le fichier `application.properties` avec vos paramètres MySQL :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/boutique_thes
spring.datasource.username=root
spring.datasource.password=votre_mot_de_passe
```

## Lancer l'application

```bash
./mvnw spring-boot:run
```

Puis ouvrir http://localhost:8080
