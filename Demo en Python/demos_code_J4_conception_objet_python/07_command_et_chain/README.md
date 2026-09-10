# Command + Chain of Responsibility : encapsuler l’action et composer les contrôles

**Slides :** 30–32

## Objectif pédagogique
Montrer une chaîne de validations puis une Command exécutée seulement si les contrôles passent.

## Adaptation Python
Chaque `Handler` reçoit une fonction `next_step`, équivalent du `Runnable` Java. La `Command` encapsule l’action finale. La chaîne décide si cette action sera atteinte.

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
