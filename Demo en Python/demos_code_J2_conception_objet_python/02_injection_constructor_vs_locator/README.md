# Démo 02 — Injection par constructeur vs Service Locator

**Slides : 12–13.** Durée : 8 à 10 min.

```bash
python demo_injection.py
```

Montrez d’abord `TokenServiceWithLocator` et demandez : **« de quoi cette classe dépend-elle ? »** La réponse n’est pas visible dans son constructeur. Elle peut échouer à l’exécution si le registre n’a pas été initialisé.

Puis montrez `TokenServiceWithInjection` : la dépendance `Clock` est obligatoire, explicite et remplaçable. `FixedClock` rend l’exécution déterministe.

Phrase importante : **l’injection n’est pas un framework ; c’est d’abord une façon de construire un objet avec ses collaborateurs**. Des frameworks peuvent automatiser l’assemblage, mais le principe existe sans conteneur.
