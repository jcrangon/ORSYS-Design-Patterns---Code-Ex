from dataclasses import dataclass
from enum import Enum
from typing import Protocol


class Customer(Enum):
    STANDARD = "STANDARD"
    PREMIUM = "PREMIUM"


@dataclass(frozen=True)
class Order:
    id: str
    customer: Customer
    total: int


@dataclass(frozen=True)
class Receipt:
    status: str
    charged: int


class PaymentPort(Protocol):
    def authorize(self, amount: int) -> bool: ...


class OrderRepository(Protocol):
    def save(self, order: Order) -> None: ...
    def exists(self, order_id: str) -> bool: ...


class Notifier(Protocol):
    def confirmation(self, order_id: str) -> None: ...


class DiscountPolicy(Protocol):
    def apply(self, customer: Customer, total: int) -> int: ...


class StandardDiscounts:
    def apply(self, customer: Customer, total: int) -> int:
        return total * 85 // 100 if customer is Customer.PREMIUM else total


class CheckoutService:
    def __init__(self, payment: PaymentPort, repository: OrderRepository,
                 notifier: Notifier, discounts: DiscountPolicy):
        self._payment = payment
        self._repository = repository
        self._notifier = notifier
        self._discounts = discounts

    def confirm(self, order: Order) -> Receipt:
        amount = self._discounts.apply(order.customer, order.total)
        if not self._payment.authorize(amount):
            return Receipt("DECLINED", 0)
        self._repository.save(order)
        self._notifier.confirmation(order.id)
        return Receipt("APPROVED", amount)
