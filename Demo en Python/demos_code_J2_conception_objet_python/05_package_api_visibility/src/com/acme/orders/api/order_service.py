from com.acme.orders._internal.order_repository import OrderRepository


class OrderService:
    def __init__(self) -> None:
        self._repository = OrderRepository()

    def create(self, customer: str) -> str:
        return self._repository.insert(customer)
