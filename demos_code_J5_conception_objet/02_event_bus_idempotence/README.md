# Data Bus : publier un événement et rendre les consumers idempotents

**Slides :** 14, 16, 22

## Objectif pédagogique
Illustrer le découplage producteur/consommateurs, puis montrer pourquoi un consumer doit tolérer la rediffusion d'un même événement.

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
