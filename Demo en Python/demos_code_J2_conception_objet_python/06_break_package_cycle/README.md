# Démo 06 — Casser une dépendance cyclique

**Slides : 39–40.** Durée : 12 min.

## Avant : orders ↔ billing

```bash
# Depuis ce dossier
PYTHONPATH=avant/src python -m com.acme.app.main
```

Dessinez : `orders → billing → orders`. Le `set_orders()` nécessaire après construction est un symptôme très parlant du cycle. Les imports de type utilisent `TYPE_CHECKING` afin que la démo reste exécutable tout en conservant la dépendance conceptuelle dans le code.

## Après : dépendance vers un contrat stable

```bash
PYTHONPATH=apres/src python -m com.acme.app.main
```

Le cycle est cassé grâce à `RefundListener`. Un vrai projet pourrait aussi utiliser un événement de domaine, une orchestration applicative, fusionner deux packages trop intimement liés ou déplacer le contrat vers le côté stable. **Ne pas tout déplacer arbitrairement dans `common`.**
