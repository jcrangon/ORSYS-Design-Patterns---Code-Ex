from com.acme.contracts.refund_listener import RefundListener


class OrderRefundListener(RefundListener):
    def refunded(self, order_id: str) -> None:
        print(f"orders: refunded {order_id}")
