from abc import ABC, abstractmethod


class PaymentService(ABC):
    @abstractmethod
    def find(self, payment_id: str) -> str: ...

    @abstractmethod
    def capture(self, payment_id: str) -> None: ...

    @abstractmethod
    def refund(self, payment_id: str) -> None: ...

    @abstractmethod
    def export_csv(self) -> str: ...

    @abstractmethod
    def purge_all(self) -> None: ...


class ReadOnlyPaymentView(PaymentService):
    def find(self, payment_id: str) -> str:
        return f"payment:{payment_id}"

    def capture(self, payment_id: str) -> None:
        raise NotImplementedError

    def refund(self, payment_id: str) -> None:
        raise NotImplementedError

    def export_csv(self) -> str:
        raise NotImplementedError

    def purge_all(self) -> None:
        raise NotImplementedError


if __name__ == "__main__":
    view: PaymentService = ReadOnlyPaymentView()
    print(view.find("P42"))
    try:
        view.refund("P42")
    except NotImplementedError:
        print("Contrat mensonger : refund() n'est pas supporté")
