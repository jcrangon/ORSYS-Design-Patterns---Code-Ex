from dataclasses import dataclass
from typing import Protocol


class Mailer(Protocol):
    def send_confirmation(self, email: str) -> None: ...


@dataclass(frozen=True)
class Order:
    email: str
    total: int


@dataclass(frozen=True)
class Receipt:
    status: str


class Checkout:
    def __init__(self, mailer: Mailer):
        self._mailer = mailer

    def confirm(self, order: Order) -> Receipt:
        receipt = Receipt("APPROVED")
        self._mailer.send_confirmation(order.email)
        return receipt
