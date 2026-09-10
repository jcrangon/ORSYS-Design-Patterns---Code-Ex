from typing import TYPE_CHECKING

if TYPE_CHECKING:
    from com.acme.orders.order_service import OrderService


class BillingService:
    # Dépendance billing -> orders : le cycle architectural est visible.
    def __init__(self) -> None:
        self._orders: "OrderService | None" = None

    def set_orders(self, orders: "OrderService") -> None:
        self._orders = orders

    def refund(self, order_id: str) -> None:
        print(f"billing: refund {order_id}")
        if self._orders is None:
            raise RuntimeError("OrderService non configuré")
        self._orders.mark_refunded(order_id)
