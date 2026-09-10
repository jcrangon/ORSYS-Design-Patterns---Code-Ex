
from collections.abc import Callable
from dataclasses import dataclass


@dataclass(frozen=True)
class ReservationConfirmed:
    event_id: str
    reservation_id: str


Listener = Callable[[ReservationConfirmed], None]


class EventBus:
    def __init__(self) -> None:
        self._listeners: list[Listener] = []

    def subscribe(self, listener: Listener) -> None:
        self._listeners.append(listener)

    def publish(self, event: ReservationConfirmed) -> None:
        print(f"[BUS] publish {event.event_id}")
        for listener in self._listeners:
            listener(event)


class EmailConsumer:
    def __init__(self) -> None:
        self._processed: set[str] = set()

    def __call__(self, event: ReservationConfirmed) -> None:
        if event.event_id in self._processed:
            print(f"[EMAIL] doublon ignoré {event.event_id}")
            return

        self._processed.add(event.event_id)
        print(f"[EMAIL] confirmation {event.reservation_id}")


def analytics_consumer(event: ReservationConfirmed) -> None:
    print(f"[ANALYTICS] reservation={event.reservation_id}")


def main() -> None:
    bus = EventBus()
    bus.subscribe(EmailConsumer())
    bus.subscribe(analytics_consumer)

    event = ReservationConfirmed("evt-001", "R-42")
    bus.publish(event)
    print("-- redelivery simulée --")
    bus.publish(event)

    print("À commenter : le bus découple, mais la fiabilité crée de nouvelles responsabilités.")


if __name__ == "__main__":
    main()
