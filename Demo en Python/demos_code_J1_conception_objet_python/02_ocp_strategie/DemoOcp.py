from abc import ABC, abstractmethod
from dataclasses import dataclass


@dataclass(frozen=True)
class Commande:
    total_cents: int


class CalculFraisLivraison(ABC):
    @abstractmethod
    def calculer(self, commande: Commande) -> int:
        pass


class Standard(CalculFraisLivraison):
    def calculer(self, commande: Commande) -> int:
        return 0 if commande.total_cents >= 5000 else 500


class Express(CalculFraisLivraison):
    def calculer(self, commande: Commande) -> int:
        return 1200


class Relais(CalculFraisLivraison):
    def calculer(self, commande: Commande) -> int:
        return 300


# Extension ajoutée sans modifier total_avec_livraison().
class Drone(CalculFraisLivraison):
    def calculer(self, commande: Commande) -> int:
        return 1800


def total_avec_livraison(c: Commande, calcul: CalculFraisLivraison) -> int:
    return c.total_cents + calcul.calculer(c)


def main() -> None:
    c = Commande(4200)

    print(f"Standard -> {total_avec_livraison(c, Standard())}")
    print(f"Express  -> {total_avec_livraison(c, Express())}")
    print(f"Relais   -> {total_avec_livraison(c, Relais())}")
    print(f"Drone    -> {total_avec_livraison(c, Drone())}")


if __name__ == "__main__":
    main()
