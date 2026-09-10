from abc import ABC, abstractmethod
from collections.abc import Callable
from dataclasses import dataclass


@dataclass(frozen=True)
class Cart:
    total: int


@dataclass(frozen=True)
class Receipt:
    id: str
    paid: int


class DiscountStrategy(ABC):
    @abstractmethod
    def apply(self, cart: Cart) -> int:
        pass


class NoDiscount(DiscountStrategy):
    def apply(self, cart: Cart) -> int:
        return cart.total


class VipDiscount(DiscountStrategy):
    def apply(self, cart: Cart) -> int:
        return round(cart.total * 0.8)


class PaymentGateway(ABC):
    @abstractmethod
    def pay(self, amount: int) -> Receipt:
        pass


class StripeGateway(PaymentGateway):
    def pay(self, amount: int) -> Receipt:
        return Receipt("stripe-001", amount)


class PaypalGateway(PaymentGateway):
    def pay(self, amount: int) -> Receipt:
        return Receipt("paypal-001", amount)


class PaymentFactory:
    @staticmethod
    def create(provider: str) -> PaymentGateway:
        gateways = {
            "stripe": StripeGateway,
            "paypal": PaypalGateway,
        }
        try:
            return gateways[provider]()
        except KeyError as exc:
            raise ValueError(provider) from exc


class AuditGatewayDecorator(PaymentGateway):
    def __init__(self, target: PaymentGateway) -> None:
        self._target = target

    def pay(self, amount: int) -> Receipt:
        print(f"[AUDIT] paiement demandé={amount}")
        receipt = self._target.pay(amount)
        print(f"[AUDIT] reçu={receipt.id}")
        return receipt


@dataclass(frozen=True)
class OrderPaid:
    receipt: Receipt


OrderPaidListener = Callable[[OrderPaid], None]


class EventBus:
    def __init__(self) -> None:
        self._listeners: list[OrderPaidListener] = []

    def subscribe(self, listener: OrderPaidListener) -> None:
        self._listeners.append(listener)

    def publish(self, event: OrderPaid) -> None:
        for listener in self._listeners:
            listener(event)


class CheckoutService:
    def __init__(
        self,
        discount: DiscountStrategy,
        gateway: PaymentGateway,
        events: EventBus,
    ) -> None:
        self._discount = discount
        self._gateway = gateway
        self._events = events

    def checkout(self, cart: Cart) -> Receipt:
        amount = self._discount.apply(cart)
        receipt = self._gateway.pay(amount)
        self._events.publish(OrderPaid(receipt))
        return receipt


def assert_eq(expected: object, actual: object, label: str) -> None:
    if expected != actual:
        raise AssertionError(f"{label} expected={expected} actual={actual}")
    print(f"[PASS] {label}")


def main() -> None:
    bus = EventBus()
    notifications: list[str] = []

    def email_listener(event: OrderPaid) -> None:
        notifications.append(f"email:{event.receipt.id}")
        print("[EMAIL] paiement confirmé")

    bus.subscribe(email_listener)

    gateway: PaymentGateway = AuditGatewayDecorator(PaymentFactory.create("stripe"))
    checkout = CheckoutService(VipDiscount(), gateway, bus)
    receipt = checkout.checkout(Cart(100))

    assert_eq(80, receipt.paid, "VIP applique 20% de remise")
    assert_eq(1, len(notifications), "un événement déclenche une notification")

    print("\nExtension à demander au groupe : ajouter ApplePay sans modifier CheckoutService.")


if __name__ == "__main__":
    main()
