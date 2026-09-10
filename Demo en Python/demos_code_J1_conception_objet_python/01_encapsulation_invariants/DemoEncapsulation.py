from enum import Enum, auto


class Statut(Enum):
    BROUILLON = auto()
    PAYEE = auto()
    EXPEDIEE = auto()


class Commande:
    """L'objet protège lui-même ses invariants métier."""

    def __init__(self) -> None:
        self._prix_cents: list[int] = []
        self._statut = Statut.BROUILLON

    @property
    def statut(self) -> Statut:
        return self._statut

    @property
    def prix_cents(self) -> tuple[int, ...]:
        # On ne renvoie pas la liste mutable interne.
        return tuple(self._prix_cents)

    def ajouter_ligne(self, prix_cents: int) -> None:
        if self._statut is not Statut.BROUILLON:
            raise RuntimeError("Commande non modifiable")
        if prix_cents <= 0:
            raise ValueError("Prix invalide")
        self._prix_cents.append(prix_cents)

    def total_cents(self) -> int:
        return sum(self._prix_cents)

    def payer(self) -> None:
        if not self._prix_cents:
            raise RuntimeError("Commande vide")
        if self._statut is not Statut.BROUILLON:
            raise RuntimeError("Etat incompatible")
        self._statut = Statut.PAYEE

    def expedier(self) -> None:
        if self._statut is not Statut.PAYEE:
            raise RuntimeError("Paiement requis")
        self._statut = Statut.EXPEDIEE

    def __str__(self) -> str:
        return f"Commande{{total={self.total_cents()} cents, statut={self._statut.name}}}"


def main() -> None:
    c = Commande()
    c.ajouter_ligne(1200)
    c.ajouter_ligne(800)
    print(c)

    c.payer()
    c.expedier()
    print(c)

    # Décommenter pendant le cours pour provoquer l'erreur :
    # c.ajouter_ligne(500)


if __name__ == "__main__":
    main()
