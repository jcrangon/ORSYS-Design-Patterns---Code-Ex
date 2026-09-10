from abc import ABC, abstractmethod


class DiscountStrategy(ABC):
    @abstractmethod
    def apply(self, total: int) -> int:
        pass


class StandardDiscount(DiscountStrategy):
    def apply(self, total: int) -> int:
        return total


class VipDiscount(DiscountStrategy):
    def apply(self, total: int) -> int:
        return round(total * 0.80)


class Checkout:
    def __init__(self, discount: DiscountStrategy) -> None:
        self._discount = discount

    def total(self, raw: int) -> int:
        return self._discount.apply(raw)


class OrderState(ABC):
    @abstractmethod
    def next(self) -> "OrderState":
        pass

    @property
    @abstractmethod
    def name(self) -> str:
        pass

    @property
    @abstractmethod
    def can_ship(self) -> bool:
        pass


class Draft(OrderState):
    def next(self) -> OrderState:
        return Paid()

    @property
    def name(self) -> str:
        return "DRAFT"

    @property
    def can_ship(self) -> bool:
        return False


class Paid(OrderState):
    def next(self) -> OrderState:
        return Shipped()

    @property
    def name(self) -> str:
        return "PAID"

    @property
    def can_ship(self) -> bool:
        return True


class Shipped(OrderState):
    def next(self) -> OrderState:
        return self

    @property
    def name(self) -> str:
        return "SHIPPED"

    @property
    def can_ship(self) -> bool:
        return False


class Order:
    def __init__(self) -> None:
        self._state: OrderState = Draft()

    def advance(self) -> None:
        self._state = self._state.next()

    def print_state(self) -> None:
        print(f"state={self._state.name}, canShip={str(self._state.can_ship).lower()}")


def main() -> None:
    print("=== Strategy : choix externe ===")
    print(f"standard 100 -> {Checkout(StandardDiscount()).total(100)}")
    print(f"VIP      100 -> {Checkout(VipDiscount()).total(100)}")

    print("\n=== State : transition interne ===")
    order = Order()
    order.print_state()
    order.advance()
    order.print_state()
    order.advance()
    order.print_state()


if __name__ == "__main__":
    main()
