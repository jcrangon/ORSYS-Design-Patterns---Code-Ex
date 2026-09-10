from typing import TYPE_CHECKING

if TYPE_CHECKING:
    from com.acme.billing.billing_service import BillingService


class OrderService:
    # Dépendance orders -> billing.
    def __init__(self, billing: "BillingService") -> None:
        self._billing = billing

    def cancel(self, order_id: str) -> None:
        print(f"orders: cancel {order_id}")
        self._billing.refund(order_id)

    def mark_refunded(self, order_id: str) -> None:
        print(f"orders: refunded {order_id}")
