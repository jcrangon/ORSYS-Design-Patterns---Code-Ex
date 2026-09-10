# Factory + Strategy : choisir sans propager les classes concrètes

**Slides :** 9–15

## Objectif pédagogique
Montrer que la Factory centralise le choix de l’implémentation tandis que Strategy porte le comportement interchangeable. Ajouter un canal de notification ne modifie pas le client.

## Adaptation Python
`Sender` est représenté par une classe abstraite (`ABC`). La Factory retourne l’implémentation concrète demandée et `NotificationService` ne dépend que de l’abstraction.

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
