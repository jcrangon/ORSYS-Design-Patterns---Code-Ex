from abc import ABC, abstractmethod


class RefundListener(ABC):
    @abstractmethod
    def refunded(self, order_id: str) -> None:
        raise NotImplementedError
