from abc import ABC, abstractmethod
from dataclasses import dataclass
from decimal import Decimal
from typing import Callable


@dataclass(frozen=True)
class Money:
    amount: Decimal
    currency: str


@dataclass(frozen=True)
class PaymentReceipt:
    reference: str
    accepted: bool


class PaymentPort(ABC):
    """Contrat formulé depuis le BESOIN du cas d'usage."""

    @abstractmethod
    def authorize(self, amount: Money, payment_token: str) -> PaymentReceipt:
        raise NotImplementedError


class StripeSdk:
    """Détail technique simulé."""

    def charge(self, cents: int, token: str) -> str:
        return f"stripe_tx_{cents}_{token[:4]}"


class StripePaymentAdapter(PaymentPort):
    """Adapter : traduit le vocabulaire du coeur vers Stripe."""

    def __init__(self, stripe: StripeSdk) -> None:
        self._stripe = stripe

    def authorize(self, amount: Money, payment_token: str) -> PaymentReceipt:
        cents = int(amount.amount * 100)
        transaction_id = self._stripe.charge(cents, payment_token)
        return PaymentReceipt(transaction_id, True)


class FakePaymentPort(PaymentPort):
    def authorize(self, amount: Money, payment_token: str) -> PaymentReceipt:
        return PaymentReceipt("fake-001", True)


class CheckoutService:
    def __init__(self, payment: PaymentPort) -> None:
        self._payment = payment

    def checkout(self, amount: Money, token: str) -> str:
        receipt = self._payment.authorize(amount, token)
        return (
            f"Paiement accepté : {receipt.reference}"
            if receipt.accepted
            else "Paiement refusé"
        )


if __name__ == "__main__":
    production_port = StripePaymentAdapter(StripeSdk())
    checkout = CheckoutService(production_port)
    print(checkout.checkout(Money(Decimal("42.50"), "EUR"), "tok_demo_123"))

    # Preuve pédagogique : le même cas d'usage fonctionne sans Stripe.
    fake = FakePaymentPort()
    print(CheckoutService(fake).checkout(Money(Decimal("10.00"), "EUR"), "test-token"))
