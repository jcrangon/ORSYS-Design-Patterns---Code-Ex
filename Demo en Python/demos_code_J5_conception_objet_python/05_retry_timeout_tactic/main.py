
from typing import Protocol


class PaymentApi(Protocol):
    def charge(self, request_id: str, amount: int) -> str:
        ...


class FlakyPaymentApi:
    def __init__(self) -> None:
        self._calls = 0

    def charge(self, request_id: str, amount: int) -> str:
        self._calls += 1
        print(f"appel fournisseur #{self._calls} requestId={request_id}")
        if self._calls < 3:
            raise TimeoutError("timeout simulé")
        return f"PAY-OK-{amount}"


class RetryingPaymentGateway:
    def __init__(self, api: PaymentApi, max_attempts: int) -> None:
        self._api = api
        self._max_attempts = max_attempts

    def charge(self, request_id: str, amount: int) -> str:
        last_error: Exception | None = None

        for _attempt in range(1, self._max_attempts + 1):
            try:
                return self._api.charge(request_id, amount)
            except TimeoutError as exc:
                last_error = exc
                print(f"retry après {exc}")

        assert last_error is not None
        raise last_error


def main() -> None:
    gateway = RetryingPaymentGateway(FlakyPaymentApi(), max_attempts=3)
    print("Résultat =", gateway.charge("req-42", 120))
    print("À commenter : retry améliore la disponibilité mais exige des opérations idempotentes.")


if __name__ == "__main__":
    main()
