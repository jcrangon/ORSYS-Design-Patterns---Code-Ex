# Démo 08 — Règles d’architecture exécutables

**Slide : 43.** Durée : 8 à 10 min.

```bash
javac DemoArchitectureRules.java
java DemoArchitectureRules
```

Cette version n’utilise aucune bibliothèque : elle simule ce que ferait un test d’architecture.

Montrez les trois scénarios :

1. graphe valide ;
2. `domain → stripe` interdit ;
3. cycle `orders ↔ billing` interdit.

Expliquez ensuite qu’en projet réel on peut utiliser **ArchUnit** côté Java, des tests de dépendances, des règles de modules, des linters ou des vérifications CI.

Point pédagogique essentiel : **l’outil sait vérifier une règle que l’équipe a explicitée ; il ne sait pas décider à votre place si la frontière métier est pertinente.**
