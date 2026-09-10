# Observer : publier un événement sans connaître les réactions

**Slides :** 29

## Objectif pédagogique
Faire voir le découplage émetteur/listeners, puis attirer l’attention sur l’ordre, les erreurs et la traçabilité.

## Adaptation Python
Les listeners sont de simples callables Python. Le bus d’événements les stocke et les exécute sans que `OrderService` connaisse les réactions concrètes.

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
