from abc import ABC, abstractmethod


class PaymentReader(ABC):
    @abstractmethod
    def find(self, payment_id: str) -> str: ...


class PaymentCaptor(ABC):
    @abstractmethod
    def capture(self, payment_id: str) -> None: ...


class RefundManager(ABC):
    @abstractmethod
    def refund(self, payment_id: str) -> None: ...


class PaymentExporter(ABC):
    @abstractmethod
    def export_csv(self) -> str: ...


class PaymentApplicationService(PaymentReader, PaymentCaptor, RefundManager, PaymentExporter):
    def find(self, payment_id: str) -> str:
        return f"payment:{payment_id}"

    def capture(self, payment_id: str) -> None:
        print(f"capture {payment_id}")

    def refund(self, payment_id: str) -> None:
        print(f"refund {payment_id}")

    def export_csv(self) -> str:
        return "id,status\nP42,CAPTURED"


class PaymentScreen:
    def __init__(self, reader: PaymentReader) -> None:
        self._reader = reader

    def render(self, payment_id: str) -> None:
        print("UI -> " + self._reader.find(payment_id))


class RefundUseCase:
    def __init__(self, reader: PaymentReader, refunds: RefundManager) -> None:
        self._reader = reader
        self._refunds = refunds

    def execute(self, payment_id: str) -> None:
        print("Avant remboursement : " + self._reader.find(payment_id))
        self._refunds.refund(payment_id)


if __name__ == "__main__":
    service = PaymentApplicationService()
    PaymentScreen(service).render("P42")
    RefundUseCase(service, service).execute("P42")
