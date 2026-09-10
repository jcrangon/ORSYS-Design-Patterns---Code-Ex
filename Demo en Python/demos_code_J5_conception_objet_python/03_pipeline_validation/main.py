
from collections.abc import Callable
from dataclasses import dataclass, replace


@dataclass(frozen=True)
class Booking:
    user: str
    room: str
    paid: bool
    tags: tuple[str, ...] = ()

    def with_tag(self, tag: str) -> "Booking":
        return replace(self, tags=(*self.tags, tag))


Step = Callable[[Booking], Booking]


def validate(booking: Booking) -> Booking:
    if not booking.user or not booking.user.strip():
        raise ValueError("user requis")
    return booking.with_tag("validated")


def enrich(booking: Booking) -> Booking:
    return booking.with_tag(f"room:{booking.room}")


def check_payment(booking: Booking) -> Booking:
    if not booking.paid:
        raise RuntimeError("paiement requis")
    return booking.with_tag("payment-ok")


def run_pipeline(input_booking: Booking, steps: list[Step]) -> Booking:
    current = input_booking
    for step in steps:
        current = step(current)
    return current


def main() -> None:
    pipeline = [validate, enrich, check_payment]

    ok = Booking("Alice", "A101", True)
    print(run_pipeline(ok, pipeline))

    try:
        run_pipeline(Booking("Bob", "A102", False), pipeline)
    except Exception as exc:
        print("Refus attendu :", exc)

    print("À commenter : chaque étape est petite, explicite, testable et recomposable.")


if __name__ == "__main__":
    main()
