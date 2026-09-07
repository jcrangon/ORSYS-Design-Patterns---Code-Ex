# Pipeline : composer un traitement étape par étape

**Slides :** 21, 22

## Objectif pédagogique
Montrer une chaîne explicite de transformations, chaque étape étant testable isolément et recomposable sans réécrire le flux complet.

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
