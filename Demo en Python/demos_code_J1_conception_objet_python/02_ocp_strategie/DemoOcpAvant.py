from dataclasses import dataclass
from enum import Enum, auto


class ModeLivraison(Enum):
    STANDARD = auto()
    EXPRESS = auto()
    RELAIS = auto()


@dataclass(frozen=True)
class Commande:
    total_cents: int


def frais(c: Commande, mode: ModeLivraison) -> int:
    if mode is ModeLivraison.STANDARD:
        return 0 if c.total_cents >= 5000 else 500
    if mode is ModeLivraison.EXPRESS:
        return 1200
    if mode is ModeLivraison.RELAIS:
        return 300
    raise ValueError(f"Mode de livraison inconnu : {mode}")


def total_avec_livraison(c: Commande, mode: ModeLivraison) -> int:
    return c.total_cents + frais(c, mode)


def main() -> None:
    c = Commande(4200)
    for mode in ModeLivraison:
        print(f"{mode.name} -> {total_avec_livraison(c, mode)}")


if __name__ == "__main__":
    main()
