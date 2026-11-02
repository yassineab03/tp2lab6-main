# Gestion des étudiants

API Spring Boot organisée en trois couches : contrôleur HTTP, service métier et dépôt JPA avec MariaDB.

## Démarrage

Prérequis : JDK 17 et MariaDB. Créer la base `studentdb`, puis adapter les paramètres de connexion dans `src/main/resources/application.properties` à votre installation.

Sous Windows :

```powershell
.\mvnw.cmd spring-boot:run
```

Documentation interactive : http://localhost:8080/swagger-ui/index.html (port Spring Boot par défaut).

## Routes

| Méthode | URL | Résultat |
| --- | --- | --- |
| POST | `/students/save` | Enregistrer un étudiant (201) |
| GET | `/students/all` | Lister les étudiants |
| GET | `/students/{id}` | Consulter une fiche (200 ou 404) |
| DELETE | `/students/delete/{id}` | Supprimer une fiche (204 ou 404) |
| GET | `/students/count` | Compter les étudiants |
| GET | `/students/byYear` | Compter les étudiants par année de naissance |

Exemple de corps JSON pour l'enregistrement :

```json
{
  "nom": "Bennani",
  "prenom": "Salma",
  "dateNaissance": "2003-05-18"
}
```

Les noms et prénoms vides, les dates absentes et les dates qui ne sont pas dans le passé sont refusés avec le statut 400.

## Vérification

```powershell
.\mvnw.cmd test
```

Les tests du contrôleur utilisent Mockito. Le test de chargement du contexte nécessite une base MariaDB accessible selon la configuration.

## Évolutions apportées

- Consultation d'un étudiant par identifiant avec gestion du cas absent.
- Validation des champs lors de l'enregistrement.
- Injection des dépendances par constructeur.

Ce projet est adapté d'une base de TP partagée. Compléter la provenance selon les consignes de l'établissement et joindre ses propres captures d'exécution.
