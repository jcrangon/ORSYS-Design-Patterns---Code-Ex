from enum import Enum, auto


class Statut(Enum):
    BROUILLON = auto()
    PAYEE = auto()
    EXPEDIEE = auto()


class Commande:
    """Version volontairement mauvaise : état métier modifiable librement."""

    def __init__(self) -> None:
        self.prix_cents: list[int] = []
        self.statut = Statut.BROUILLON


def main() -> None:
    c = Commande()
    c.prix_cents.append(1200)
    c.prix_cents.append(-500)       # incohérent, mais autorisé
    c.statut = Statut.EXPEDIEE      # expédition sans paiement

    print(f"Prix = {c.prix_cents}")
    print(f"Statut = {c.statut.name}")
    print("=> Python accepte lui aussi cet état métier incohérent.")


if __name__ == "__main__":
    main()
