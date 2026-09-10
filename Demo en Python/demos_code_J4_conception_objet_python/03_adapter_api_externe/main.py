from abc import ABC, abstractmethod
from dataclasses import dataclass


@dataclass(frozen=True)
class Money:
    cents: int

    @staticmethod
    def euros(euros: int) -> "Money":
        return Money(euros * 100)


@dataclass(frozen=True)
class Receipt:
    id: str


class PaymentGateway(ABC):
    @abstractmethod
    def pay(self, amount: Money) -> Receipt:
        pass


# API externe que l'on ne contrôle pas
class StripeClient:
    @dataclass(frozen=True)
    class StripeCharge:
        charge_id: str
        ok: bool

    def charge(self, amount_in_cents: int) -> "StripeClient.StripeCharge":
        print(f"[Stripe SDK] charge {amount_in_cents} cents")
        return self.StripeCharge("ch_123", True)


class StripePaymentAdapter(PaymentGateway):
    def __init__(self, client: StripeClient) -> None:
        self._client = client

    def pay(self, amount: Money) -> Receipt:
        charge = self._client.charge(amount.cents)
        if not charge.ok:
            raise RuntimeError("Paiement refusé")
        return Receipt(charge.charge_id)


class Checkout:
    def __init__(self, gateway: PaymentGateway) -> None:
        self._gateway = gateway

    def checkout(self, total: Money) -> Receipt:
        return self._gateway.pay(total)


def main() -> None:
    checkout = Checkout(StripePaymentAdapter(StripeClient()))
    print(f"Receipt domaine = {checkout.checkout(Money.euros(49))}")
    print("À commenter : Checkout ne dépend d'aucun type Stripe.")


if __name__ == "__main__":
    main()
