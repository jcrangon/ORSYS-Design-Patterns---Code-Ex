# Strategy versus State : même forme, intention différente

**Slides :** 27–28

## Objectif pédagogique
Montrer concrètement qui choisit le comportement : le client pour Strategy, l’objet lui-même via ses transitions pour State.

## Adaptation Python
Strategy et State sont tous deux modélisés par des abstractions et plusieurs implémentations. La différence importante n’est pas la syntaxe : avec Strategy, le client choisit ; avec State, `Order` évolue en changeant lui-même son état.

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
