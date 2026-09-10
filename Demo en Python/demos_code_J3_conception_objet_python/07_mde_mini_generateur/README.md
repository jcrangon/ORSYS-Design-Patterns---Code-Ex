# Démo 07 — mini MDE réellement testé avec pytest

Commande : `python -m pytest -q`

pytest vérifie :
1. le contenu du fichier Python généré ;
2. que le fichier généré compile réellement avec `py_compile` ;
3. qu'un modèle invalide est refusé.

Le modèle reste volontairement minimal :
```text
entity Order
field id str
field total int
```
