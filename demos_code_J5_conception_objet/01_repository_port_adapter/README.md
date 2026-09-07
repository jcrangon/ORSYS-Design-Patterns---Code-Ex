# Repository : isoler le domaine de la persistance

**Slides :** 13, 19, 22

## Objectif pédagogique
Montrer qu'un cas d'usage dépend d'un port `ReservationRepository`, tandis que la persistance concrète reste interchangeable. Le fake en mémoire sert ensuite de support de test.

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
