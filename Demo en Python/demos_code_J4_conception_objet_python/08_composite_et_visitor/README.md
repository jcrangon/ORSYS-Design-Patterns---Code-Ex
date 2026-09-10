# Composite + Visitor : structure stable, opérations extensibles

**Slides :** 19, 34

## Objectif pédagogique
Montrer un arbre de documents uniforme grâce à Composite puis ajouter une opération de calcul avec Visitor sans exposer toute la structure.

## Adaptation Python
`FileNode` et `Folder` implémentent la même abstraction `Node`. Chaque nœud sait accepter un Visitor. `Folder` propage ensuite la visite à ses enfants.

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
