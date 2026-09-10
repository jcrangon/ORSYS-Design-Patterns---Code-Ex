from dataclasses import dataclass
from typing import Protocol


@dataclass(frozen=True)
class Order:
    id: str
    total: int


@dataclass(frozen=True)
class Receipt:
    status: str


class PaymentPort(Protocol):
    def pay(self, order: Order) -> bool: ...


class OrderRepository(Protocol):
    def save(self, order: Order) -> None: ...
    def exists(self, order_id: str) -> bool: ...


class Notifier(Protocol):
    def confirmation(self, order: Order) -> None: ...


class Audit(Protocol):
    def publish(self, event: str) -> None: ...


class CheckoutService:
    def __init__(self, payment: PaymentPort, repository: OrderRepository,
                 notifier: Notifier, audit: Audit):
        self._payment = payment
        self._repository = repository
        self._notifier = notifier
        self._audit = audit

    def confirm(self, order: Order) -> Receipt:
        if not self._payment.pay(order):
            return Receipt("DECLINED")
        self._repository.save(order)
        self._notifier.confirmation(order)
        self._audit.publish("ORDER_CONFIRMED")
        return Receipt("APPROVED")
