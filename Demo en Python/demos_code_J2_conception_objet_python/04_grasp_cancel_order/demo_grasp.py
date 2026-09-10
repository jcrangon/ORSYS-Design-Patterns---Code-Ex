from abc import ABC, abstractmethod
from enum import Enum, auto


class OrderStatus(Enum):
    CREATED = auto()
    PAID = auto()
    SHIPPED = auto()
    CANCELLED = auto()


class Order:
    def __init__(self, order_id: str, status: OrderStatus, paid_amount_cents: int) -> None:
        self._id = order_id
        self._status = status
        self._paid_amount_cents = paid_amount_cents

    @property
    def id(self) -> str:
        return self._id

    @property
    def paid_amount_cents(self) -> int:
        return self._paid_amount_cents

    @property
    def status(self) -> OrderStatus:
        return self._status

    # Information Expert : Order possède l'état nécessaire pour décider.
    def can_be_cancelled(self) -> bool:
        return self._status not in (OrderStatus.SHIPPED, OrderStatus.CANCELLED)

    def cancel(self) -> None:
        if not self.can_be_cancelled():
            raise RuntimeError(f"Commande non annulable : {self._status.name}")
        self._status = OrderStatus.CANCELLED


class OrderRepository(ABC):
    @abstractmethod
    def get(self, order_id: str) -> Order: ...

    @abstractmethod
    def save(self, order: Order) -> None: ...


class RefundPort(ABC):
    @abstractmethod
    def refund(self, order_id: str, cents: int) -> None: ...


class NotificationPort(ABC):
    @abstractmethod
    def cancelled(self, order_id: str) -> None: ...


class ConsoleRefundPort(RefundPort):
    def refund(self, order_id: str, cents: int) -> None:
        print(f"Refund {cents} cents pour {order_id}")


class ConsoleNotificationPort(NotificationPort):
    def cancelled(self, order_id: str) -> None:
        print(f"Email : commande {order_id} annulée")


# Controller GRASP : coordonne le cas d'usage, sans absorber la règle métier.
class CancelOrderUseCase:
    def __init__(
        self,
        orders: OrderRepository,
        refunds: RefundPort,
        notifications: NotificationPort,
    ) -> None:
        self._orders = orders
        self._refunds = refunds
        self._notifications = notifications

    def execute(self, order_id: str) -> None:
        order = self._orders.get(order_id)
        order.cancel()
        if order.paid_amount_cents > 0:
            self._refunds.refund(order.id, order.paid_amount_cents)
        self._orders.save(order)
        self._notifications.cancelled(order.id)


# Pure Fabrication : dépôt mémoire pour la démo, pas une responsabilité d'Order.
class InMemoryOrderRepository(OrderRepository):
    def __init__(self) -> None:
        self._data: dict[str, Order] = {}

    def add(self, order: Order) -> None:
        self._data[order.id] = order

    def get(self, order_id: str) -> Order:
        return self._data[order_id]

    def save(self, order: Order) -> None:
        self._data[order.id] = order


if __name__ == "__main__":
    repo = InMemoryOrderRepository()
    repo.add(Order("O-42", OrderStatus.PAID, 12_900))

    use_case = CancelOrderUseCase(repo, ConsoleRefundPort(), ConsoleNotificationPort())
    use_case.execute("O-42")
    print("Statut final : " + repo.get("O-42").status.name)
