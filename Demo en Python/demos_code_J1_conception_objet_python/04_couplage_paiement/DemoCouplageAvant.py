from dataclasses import dataclass


@dataclass(frozen=True)
class StripeResponse:
    status: str


class StripeClient:
    """Simulation locale d'un SDK externe : aucune vraie dépendance réseau."""

    def charge(self, cents: int) -> StripeResponse:
        print(f"[Stripe SDK simulé] charge {cents} cents")
        return StripeResponse("paid")


class ValiderCommande:
    def __init__(self) -> None:
        # Dépendance créée en dur : le cas d'usage connaît Stripe.
        self.stripe = StripeClient()

    def executer(self, total_cents: int) -> bool:
        response = self.stripe.charge(total_cents)
        return response.status == "paid"


def main() -> None:
    ok = ValiderCommande().executer(4200)
    print(f"Paiement accepté = {ok}")


if __name__ == "__main__":
    main()
