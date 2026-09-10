# Démo 03 — LSP : Rectangle / Carré mutable

**Moment :** slide 28.

## Démonstration de la violation

```bash
python DemoLsp.py
```

La fin de la sortie contient :

```text
Rectangle : OK
AssertionError: Le client attendait 50 mais obtient 25
```

Le point à commenter n'est pas « un carré n'est pas un rectangle en mathématiques ». Le point est : **le contrat mutable de `Rectangle` promet que largeur et hauteur peuvent être changées indépendamment**. `Carre` ne peut pas tenir cette promesse.

Python autorise l'héritage exactement comme Java ici ; cela ne garantit pas davantage la substituabilité comportementale.

## Une correction possible

```bash
python DemoLspCorrige.py
```

Ici, `Rectangle` et `Carre` sont immuables grâce à `@dataclass(frozen=True)` et partagent seulement la capacité réellement commune :

```python
class Forme(ABC):
    @abstractmethod
    def aire(self) -> int:
        pass
```

Sortie :

```text
Rectangle : aire = 50
Carre : aire = 25
```

**Message oral :** LSP est comportemental. L'héritage et l'absence d'erreur d'exécution ne suffisent pas à garantir la substituabilité.
