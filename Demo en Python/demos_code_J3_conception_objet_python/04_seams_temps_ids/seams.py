from dataclasses import dataclass
from datetime import datetime
from typing import Protocol
from uuid import UUID


class ClockPort(Protocol):
    def now(self) -> datetime: ...


class IdGenerator(Protocol):
    def next(self) -> UUID: ...


class CouponService:
    def __init__(self, clock: ClockPort):
        self._clock = clock

    def valid_until(self, expiration: datetime) -> bool:
        return self._clock.now() < expiration


@dataclass(frozen=True)
class Order:
    id: UUID
    total: int
    created_at: datetime


class OrderFactory:
    def __init__(self, ids: IdGenerator, clock: ClockPort):
        self._ids = ids
        self._clock = clock

    def create(self, total: int) -> Order:
        return Order(self._ids.next(), total, self._clock.now())
