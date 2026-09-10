from abc import ABC, abstractmethod
from dataclasses import dataclass


class Forme(ABC):
    @abstractmethod
    def aire(self) -> int:
        pass


@dataclass(frozen=True)
class Rectangle(Forme):
    largeur: int
    hauteur: int

    def __post_init__(self) -> None:
        if self.largeur <= 0 or self.hauteur <= 0:
            raise ValueError("Dimensions invalides")

    def aire(self) -> int:
        return self.largeur * self.hauteur


@dataclass(frozen=True)
class Carre(Forme):
    cote: int

    def __post_init__(self) -> None:
        if self.cote <= 0:
            raise ValueError("Côté invalide")

    def aire(self) -> int:
        return self.cote * self.cote


def afficher_aire(f: Forme) -> None:
    print(f"{type(f).__name__} : aire = {f.aire()}")


def main() -> None:
    afficher_aire(Rectangle(10, 5))
    afficher_aire(Carre(5))


if __name__ == "__main__":
    main()
