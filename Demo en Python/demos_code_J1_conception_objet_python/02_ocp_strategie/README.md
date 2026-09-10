# Démo 02 — OCP : du `if` à une stratégie

**Moment :** slides 23–24.

## Version avant

```bash
python DemoOcpAvant.py
```

Faire remarquer que la fonction centrale connaît toutes les variantes :

```python
def frais(c: Commande, mode: ModeLivraison) -> int:
    if mode is ModeLivraison.STANDARD:
        ...
    if mode is ModeLivraison.EXPRESS:
        ...
    if mode is ModeLivraison.RELAIS:
        ...
```

Ajouter `DRONE` oblige à rouvrir l'énumération et cette fonction.

## Version après

```bash
python DemoOcp.py
```

Ici `ABC` + `@abstractmethod` jouent le rôle pédagogique de l'interface Java :

```python
class CalculFraisLivraison(ABC):
    @abstractmethod
    def calculer(self, commande: Commande) -> int:
        pass
```

La classe `Drone` est déjà présente pour la démonstration. Montrez-la, puis revenez à :

```python
def total_avec_livraison(c: Commande, calcul: CalculFraisLivraison) -> int:
    return c.total_cents + calcul.calculer(c)
```

**Question à poser :** « Pour ajouter Drone, quelles lignes de cette fonction ai-je modifiées ? »

Réponse : aucune.

**Point important :** un `if`, un `match` ou un dictionnaire de fonctions ne sont pas mauvais en soi. Le refactoring vers Strategy devient intéressant quand l'axe de variation est réel, répété et coûteux à rouvrir.
