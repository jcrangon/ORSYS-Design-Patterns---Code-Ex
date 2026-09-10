from decimal import Decimal


class StripeSdk:
    def charge(self, cents: int, token: str) -> str:
        return f"stripe_tx_{cents}_{token[:4]}"


class CheckoutService:
    def __init__(self) -> None:
        # Le cas d'usage dépend DIRECTEMENT d'un détail fournisseur.
        self._stripe = StripeSdk()

    def checkout(self, amount: Decimal, card_token: str) -> str:
        cents = int(amount * 100)
        stripe_id = self._stripe.charge(cents, card_token)
        return f"Paiement accepté : {stripe_id}"


if __name__ == "__main__":
    print(CheckoutService().checkout(Decimal("42.50"), "tok_demo_123"))
