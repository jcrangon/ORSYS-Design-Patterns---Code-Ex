from com.acme.billing.billing_service import BillingService


class OrderService:
    def __init__(self, billing: BillingService) -> None:
        self._billing = billing

    def cancel(self, order_id: str) -> None:
        print(f"orders: cancel {order_id}")
        self._billing.refund(order_id)
