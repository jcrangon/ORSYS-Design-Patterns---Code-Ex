# Builder : construire par étapes et protéger les invariants

**Slides :** 12–14

## Objectif pédagogique
Faire voir qu’un Builder ne sert pas seulement à rendre l’appel joli : `build()` est le point de validation des invariants de l’objet.

## Adaptation Python
`Item` et `Order` utilisent des `dataclass`. Le constructeur de `Order` n’est pas utilisé directement dans la démo : le `Builder` accumule les informations et `build()` vérifie les invariants avant de créer l’objet final.

## Exécution

### Windows
```bat
run.bat
```

### Linux / macOS
```bash
./run.sh
```

Le script exécute `main.py` avec Python.
