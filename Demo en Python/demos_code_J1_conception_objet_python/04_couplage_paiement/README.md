# Démo 04 — Couplage à un fournisseur externe

**Moment :** slide 31. Démo optionnelle si le temps le permet.

Aucune dépendance Stripe réelle n'est nécessaire : le SDK est simulé pour garder la démo instantanée et hors réseau.

## Avant : le cas d’usage connaît le fournisseur

```bash
python DemoCouplageAvant.py
```

Le cas d'usage connaît `StripeClient`, `StripeResponse` et la chaîne `"paid"`. Le vocabulaire technique fuit dans le métier.

## Après : contrat métier + adaptateur

```bash
python DemoCouplage.py
```

À montrer :

```python
class PaiementPort(ABC):
    @abstractmethod
    def payer(self, cents: int) -> bool:
        pass
```

Puis :

```python
class ValiderCommande:
    def __init__(self, paiement: PaiementPort) -> None:
        self.paiement = paiement
```

**Message oral :** le cas d'usage dépend de la capacité dont il a besoin. L'adaptateur dépend du fournisseur. Le fournisseur n'impose plus son modèle au cœur métier.

Le `FakePaiement` permet aussi de faire le lien avec la testabilité qui sera approfondie plus tard.

> Remarque Python : on pourrait également représenter le port avec `typing.Protocol` et profiter du typage structurel. `ABC` est utilisé ici car il ressemble davantage à l'interface Java montrée dans le cours.
