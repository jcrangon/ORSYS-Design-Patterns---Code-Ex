# Règle d’architecture automatisable : détecter une dépendance interdite

**Slides :** 28

## Objectif pédagogique
Simuler une règle de CI qui interdit au domaine de dépendre de l'infrastructure. L'objectif est de montrer le principe sans bibliothèque externe.

## Exécution

### Windows
```bat
run.bat
```

### Linux / macOS
```bash
./run.sh
```

Le script compile `Main.java` dans `out/` puis exécute `Main`. Aucune dépendance externe ni accès réseau n'est nécessaire.
