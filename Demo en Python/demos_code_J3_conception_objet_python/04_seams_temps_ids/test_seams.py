from datetime import datetime, timezone
from uuid import UUID
from seams import CouponService, OrderFactory

NOW = datetime(2026, 9, 6, 10, 0, 0, tzinfo=timezone.utc)
ID = UUID("00000000-0000-0000-0000-000000000001")


class FixedClock:
    def now(self):
        return NOW


class FixedIds:
    def next(self):
        return ID


def test_clock_can_be_controlled():
    service = CouponService(FixedClock())
    assert service.valid_until(datetime(2026, 9, 7, tzinfo=timezone.utc))
    assert not service.valid_until(datetime(2026, 9, 6, 9, tzinfo=timezone.utc))


def test_id_and_creation_time_are_deterministic():
    factory = OrderFactory(FixedIds(), FixedClock())
    order = factory.create(120)
    assert order.id == ID
    assert order.created_at == NOW
    assert order.total == 120
