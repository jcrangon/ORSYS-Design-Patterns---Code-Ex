# Démo 01 — Red, Green, Refactor

**Slides associées :** 13 à 16

## Objectif pédagogique
Montrer que TDD est une boucle de conception : un comportement échoue, on écrit le minimum, puis on améliore la structure sans modifier le contrat.

## Fichiers à ouvrir
`RedPhase.java`, `GreenPhase.java`, `RefactorPhase.java`

## Exécution
```bash
./run.sh
```

## À faire verbaliser
Dans Red, l’échec est attendu. Dans Green, on accepte une condition simple. Dans Refactor, on introduit une politique uniquement parce que plusieurs comportements rendent cette abstraction utile.
