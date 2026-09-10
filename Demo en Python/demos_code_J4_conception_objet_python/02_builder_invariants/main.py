from __future__ import annotations
from dataclasses import dataclass


@dataclass(frozen=True)
class Item:
    name: str
    qty: int

    def __post_init__(self) -> None:
        if self.qty <= 0:
            raise ValueError("qty > 0 obligatoire")


@dataclass(frozen=True)
class Order:
    customer: str
    items: tuple[Item, ...]
    coupon: str | None = None

    @staticmethod
    def builder() -> "OrderBuilder":
        return OrderBuilder()


class OrderBuilder:
    def __init__(self) -> None:
        self._customer: str | None = None
        self._items: list[Item] = []
        self._coupon: str | None = None

    def customer(self, customer: str) -> "OrderBuilder":
        self._customer = customer
        return self

    def add_item(self, name: str, qty: int) -> "OrderBuilder":
        self._items.append(Item(name, qty))
        return self

    def coupon(self, coupon: str) -> "OrderBuilder":
        self._coupon = coupon
        return self

    def build(self) -> Order:
        if self._customer is None or not self._customer.strip():
            raise ValueError("customer obligatoire")
        if not self._items:
            raise ValueError("au moins un article")
        return Order(self._customer, tuple(self._items), self._coupon)


def main() -> None:
    print("=== Construction valide ===")
    order = (
        Order.builder()
        .customer("Alice")
        .add_item("Livre", 2)
        .coupon("WELCOME")
        .build()
    )
    print(order)

    print("\n=== Construction invalide ===")
    try:
        Order.builder().customer("Alice").build()
    except ValueError as exc:
        print(f"Refus attendu : {exc}")


if __name__ == "__main__":
    main()
