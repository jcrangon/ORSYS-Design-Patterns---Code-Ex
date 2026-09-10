from collections.abc import Callable
from dataclasses import dataclass


@dataclass(frozen=True)
class OrderConfirmed:
    order_id: str


Listener = Callable[[OrderConfirmed], None]


class EventBus:
    def __init__(self) -> None:
        self._listeners: list[Listener] = []

    def subscribe(self, listener: Listener) -> None:
        self._listeners.append(listener)

    def publish(self, event: OrderConfirmed) -> None:
        for listener in self._listeners:
            try:
                listener(event)
            except RuntimeError as exc:
                print(f"[ERREUR LISTENER] {exc}")


class OrderService:
    def __init__(self, events: EventBus) -> None:
        self._events = events

    def confirm(self, order_id: str) -> None:
        print(f"Commande {order_id} confirmée")
        self._events.publish(OrderConfirmed(order_id))


def failing_billing_listener(event: OrderConfirmed) -> None:
    raise RuntimeError("facturation indisponible")


def main() -> None:
    bus = EventBus()
    bus.subscribe(lambda event: print(f"[EMAIL] {event.order_id}"))
    bus.subscribe(lambda event: print(f"[ANALYTICS] {event.order_id}"))
    bus.subscribe(failing_billing_listener)

    OrderService(bus).confirm("42")
    print("À commenter : OrderService ignore combien de réactions existent.")


if __name__ == "__main__":
    main()
