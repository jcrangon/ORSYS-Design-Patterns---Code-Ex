from abc import ABC, abstractmethod
from collections.abc import Callable
from dataclasses import dataclass


@dataclass(frozen=True)
class Request:
    user: str | None
    amount: int


class Handler(ABC):
    @abstractmethod
    def handle(self, request: Request, next_step: Callable[[], None]) -> None:
        pass


class AuthHandler(Handler):
    def handle(self, request: Request, next_step: Callable[[], None]) -> None:
        if request.user is None:
            raise RuntimeError("non authentifié")
        print("auth OK")
        next_step()


class QuotaHandler(Handler):
    def handle(self, request: Request, next_step: Callable[[], None]) -> None:
        if request.amount > 1000:
            raise RuntimeError("quota dépassé")
        print("quota OK")
        next_step()


class Command(ABC):
    @abstractmethod
    def execute(self) -> None:
        pass


class CapturePayment(Command):
    def __init__(self, request: Request) -> None:
        self._request = request

    def execute(self) -> None:
        print(f"CAPTURE {self._request.amount}€ pour {self._request.user}")


def run_chain(request: Request, first: Handler, second: Handler, command: Command) -> None:
    first.handle(
        request,
        lambda: second.handle(request, command.execute),
    )


def main() -> None:
    print("=== Requête valide ===")
    request = Request("alice", 250)
    run_chain(request, AuthHandler(), QuotaHandler(), CapturePayment(request))

    print("\n=== Requête refusée ===")
    request = Request("bob", 1500)
    try:
        run_chain(request, AuthHandler(), QuotaHandler(), CapturePayment(request))
    except RuntimeError as exc:
        print(f"STOP : {exc}")


if __name__ == "__main__":
    main()
