from com.acme.orders.order_service import OrderService
from com.acme.orders.order_refund_listener import OrderRefundListener
from com.acme.billing.billing_service import BillingService


def main() -> None:
    listener = OrderRefundListener()
    billing = BillingService(listener)
    orders = OrderService(billing)
    orders.cancel("O-42")


if __name__ == "__main__":
    main()
