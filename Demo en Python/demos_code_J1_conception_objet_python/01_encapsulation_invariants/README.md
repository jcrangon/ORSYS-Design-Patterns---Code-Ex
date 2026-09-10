# Démo 01 — Encapsulation et invariants

**Moment :** après la slide 7.

## 1. Montrer le problème

```bash
python DemoEncapsulationAvant.py
```

À expliquer : en Python aussi, rendre l'état librement accessible ne crée aucune barrière métier. Le programme accepte un prix négatif et une commande expédiée sans paiement.

> Python n'a pas de `private` strict comme Java : le préfixe `_` est une convention. L'encapsulation repose donc surtout sur l'API publique proposée par l'objet.

## 2. Montrer l’objet qui protège ses invariants

```bash
python DemoEncapsulation.py
```

Sortie attendue :

```text
Commande{total=2000 cents, statut=BROUILLON}
Commande{total=2000 cents, statut=EXPEDIEE}
```

À montrer :

```python
@property
def prix_cents(self) -> tuple[int, ...]:
    return tuple(self._prix_cents)
```

La collection mutable interne n'est pas exposée directement.

## 3. Déclencher volontairement une violation

Décommenter dans `main()` :

```python
c.ajouter_ligne(500)
```

Relancer. Une exception doit apparaître :

```text
RuntimeError: Commande non modifiable
```

**Message oral :** l'exception est levée au plus près de la règle. Tous les appelants bénéficient donc du même garde-fou.
