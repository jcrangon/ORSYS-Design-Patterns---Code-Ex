class Rectangle:
    def __init__(self) -> None:
        self.largeur = 0
        self.hauteur = 0

    def set_largeur(self, largeur: int) -> None:
        self.largeur = largeur

    def set_hauteur(self, hauteur: int) -> None:
        self.hauteur = hauteur

    def aire(self) -> int:
        return self.largeur * self.hauteur


class Carre(Rectangle):
    def set_largeur(self, cote: int) -> None:
        super().set_largeur(cote)
        super().set_hauteur(cote)

    def set_hauteur(self, cote: int) -> None:
        super().set_largeur(cote)
        super().set_hauteur(cote)


def scenario_client(r: Rectangle) -> None:
    r.set_largeur(10)
    r.set_hauteur(5)

    if r.aire() != 50:
        raise AssertionError(
            f"Le client attendait 50 mais obtient {r.aire()}"
        )


def main() -> None:
    scenario_client(Rectangle())
    print("Rectangle : OK")

    scenario_client(Carre())  # échoue
    print("Carre : OK")


if __name__ == "__main__":
    main()
