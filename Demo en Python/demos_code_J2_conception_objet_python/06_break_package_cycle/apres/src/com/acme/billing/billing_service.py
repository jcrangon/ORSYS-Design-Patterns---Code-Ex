from com.acme.contracts.refund_listener import RefundListener


class BillingService:
    def __init__(self, listener: RefundListener) -> None:
        self._listener = listener

    def refund(self, order_id: str) -> None:
        print(f"billing: refund {order_id}")
        self._listener.refunded(order_id)
