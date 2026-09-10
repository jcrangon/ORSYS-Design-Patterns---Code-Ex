from com.acme.orders.order_service import OrderService
from com.acme.billing.billing_service import BillingService


def main() -> None:
    billing = BillingService()
    orders = OrderService(billing)
    billing.set_orders(orders)  # construction pénible : symptôme visible du cycle
    orders.cancel("O-42")


if __name__ == "__main__":
    main()
