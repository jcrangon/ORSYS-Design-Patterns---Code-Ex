
from collections.abc import Callable
from dataclasses import dataclass
from typing import Protocol


@dataclass(frozen=True)
class Reservation:
    id: str
    room: str
    user: str
    amount: int


class ReservationRepository(Protocol):
    def save(self, reservation: Reservation) -> None:
        ...


class PaymentGateway(Protocol):
    def charge(self, reservation_id: str, amount: int) -> str:
        ...


@dataclass(frozen=True)
class ReservationConfirmed:
    id: str
    payment_id: str


class InMemoryReservationRepository:
    def __init__(self) -> None:
        self.saved: list[Reservation] = []

    def save(self, reservation: Reservation) -> None:
        self.saved.append(reservation)
        print(f"[REPO] saved {reservation.id}")


class FakePaymentGateway:
    def charge(self, reservation_id: str, amount: int) -> str:
        print(f"[PAYMENT] {amount}€ for {reservation_id}")
        return "pay-001"


Listener = Callable[[ReservationConfirmed], None]


class EventBus:
    def __init__(self) -> None:
        self._listeners: list[Listener] = []

    def subscribe(self, consumer: Listener) -> None:
        self._listeners.append(consumer)

    def publish(self, event: ReservationConfirmed) -> None:
        for consumer in self._listeners:
            consumer(event)


class ConfirmReservation:
    def __init__(
        self,
        repo: ReservationRepository,
        payment: PaymentGateway,
        bus: EventBus,
    ) -> None:
        self._repo = repo
        self._payment = payment
        self._bus = bus

    def execute(self, reservation: Reservation) -> None:
        payment_id = self._payment.charge(reservation.id, reservation.amount)
        self._repo.save(reservation)
        self._bus.publish(ReservationConfirmed(reservation.id, payment_id))


def main() -> None:
    repo = InMemoryReservationRepository()
    bus = EventBus()

    bus.subscribe(lambda event: print(f"[EMAIL] confirmation {event.id}"))
    bus.subscribe(lambda event: print(f"[ANALYTICS] reservation {event.id}"))

    use_case = ConfirmReservation(repo, FakePaymentGateway(), bus)
    use_case.execute(Reservation("R-42", "A101", "Alice", 120))

    print("Assertions : saved=", len(repo.saved) == 1, sep="")
    print("À commenter : le cœur orchestre des ports ; l'infrastructure reste remplaçable et testable.")


if __name__ == "__main__":
    main()
