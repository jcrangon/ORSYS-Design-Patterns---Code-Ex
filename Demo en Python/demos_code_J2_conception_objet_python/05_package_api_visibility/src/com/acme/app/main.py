from com.acme.orders import OrderService
# Odeur architecturale : techniquement possible, mais à éviter :
# from com.acme.orders._internal.order_repository import OrderRepository


def main() -> None:
    orders = OrderService()
    print("Créée : " + orders.create("Alice"))


if __name__ == "__main__":
    main()
