# Démo 05 — API publique d’un package en Python

**Slides : 30 à 33.** Durée : 8 à 10 min.

Depuis ce dossier :

```bash
# Windows PowerShell
$env:PYTHONPATH="src"
python -m com.acme.app.main

# Linux/macOS/Git Bash
PYTHONPATH=src python -m com.acme.app.main
```

En Python, il n’existe pas de `private package` équivalent à Java. On matérialise la frontière avec :

- une **API publique** réexportée par `com.acme.orders` ;
- un sous-package `_internal`, dont le préfixe `_` dit « détail interne » ;
- `__all__` pour documenter ce qui est exporté ;
- éventuellement des règles de lint/architecture pour rendre la convention exécutable.

### Manipulation pédagogique

Décommentez l’import direct de `_internal.order_repository` dans `main.py`. Dites : **« Python l’autorise techniquement ; est-ce pour autant une bonne dépendance architecturale ? »**
