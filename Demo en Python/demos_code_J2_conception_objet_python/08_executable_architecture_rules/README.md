# Démo 08 — Règles d’architecture exécutables

**Slide : 43.** Durée : 8 à 10 min.

```bash
python demo_architecture_rules.py
```

Cette version n’utilise aucune bibliothèque : elle simule un test d’architecture.

Montrez :
1. graphe valide ;
2. `domain → stripe` interdit ;
3. cycle `orders ↔ billing` interdit.

En projet Python réel, on peut compléter cela avec des tests, des linters d’imports, des outils de dépendances et la CI. L’outil **vérifie une règle explicitée par l’équipe** ; il ne décide pas à votre place si la frontière métier est pertinente.
