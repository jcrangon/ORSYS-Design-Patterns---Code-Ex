
from dataclasses import dataclass
from typing import Protocol


@dataclass(frozen=True)
class ReservationId:
    value: str


@dataclass(frozen=True)
class Reservation:
    id: ReservationId
    room: str
    user: str


class ReservationRepository(Protocol):
    """Port : ce dont le cas d'usage a besoin pour persister."""

    def find_by_id(self, reservation_id: ReservationId) -> Reservation | None:
        ...

    def save(self, reservation: Reservation) -> None:
        ...


class InMemoryReservationRepository:
    """Adapter concret en mémoire : remplaçable par SQL, MongoDB, fichier..."""

    def __init__(self) -> None:
        self._data: dict[ReservationId, Reservation] = {}

    def find_by_id(self, reservation_id: ReservationId) -> Reservation | None:
        return self._data.get(reservation_id)

    def save(self, reservation: Reservation) -> None:
        self._data[reservation.id] = reservation


class ReservationService:
    def __init__(self, repo: ReservationRepository) -> None:
        self._repo = repo

    def create(self, id_: str, room: str, user: str) -> Reservation:
        reservation_id = ReservationId(id_)
        if self._repo.find_by_id(reservation_id) is not None:
            raise RuntimeError("Déjà réservée")

        reservation = Reservation(reservation_id, room, user)
        self._repo.save(reservation)
        return reservation


def main() -> None:
    repo: ReservationRepository = InMemoryReservationRepository()
    service = ReservationService(repo)

    print(service.create("R-42", "A101", "Alice"))
    print("Persisté ?", repo.find_by_id(ReservationId("R-42")) is not None)
    print("À commenter : le service ne connaît ni SQL, ni MongoDB, ni fichier.")


if __name__ == "__main__":
    main()
