from abc import ABC, abstractmethod


# Le contrat est exprimé dans le vocabulaire du client métier.
class PaiementPort(ABC):
    @abstractmethod
    def payer(self, cents: int) -> bool:
        pass


# Simulation d'un SDK externe.
class StripeClient:
    def charge(self, cents: int) -> str:
        print(f"[Stripe SDK simulé] charge {cents} cents")
        return "paid"


# L'adaptateur traduit le SDK externe vers le contrat métier.
class StripeAdapter(PaiementPort):
    def __init__(self, stripe: StripeClient) -> None:
        self.stripe = stripe

    def payer(self, cents: int) -> bool:
        return self.stripe.charge(cents) == "paid"


# Fake pratique pour tester/démontrer sans fournisseur externe.
class FakePaiement(PaiementPort):
    def __init__(self, resultat: bool) -> None:
        self.resultat = resultat

    def payer(self, cents: int) -> bool:
        print(f"[Fake] payer {cents} cents -> {self.resultat}")
        return self.resultat


class ValiderCommande:
    def __init__(self, paiement: PaiementPort) -> None:
        self.paiement = paiement

    def executer(self, total_cents: int) -> bool:
        return self.paiement.payer(total_cents)


def main() -> None:
    prod = ValiderCommande(
        StripeAdapter(StripeClient())
    )
    print(f"Avec Stripe = {prod.executer(4200)}")

    test = ValiderCommande(FakePaiement(False))
    print(f"Avec Fake = {test.executer(4200)}")


if __name__ == "__main__":
    main()
