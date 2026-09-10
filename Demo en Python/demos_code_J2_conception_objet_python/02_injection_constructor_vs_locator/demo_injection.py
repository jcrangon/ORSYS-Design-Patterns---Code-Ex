from abc import ABC, abstractmethod
import time
from typing import Any, TypeVar, Type


class Clock(ABC):
    @abstractmethod
    def now(self) -> int:
        raise NotImplementedError


class SystemClock(Clock):
    def now(self) -> int:
        return int(time.time() * 1000)


class FixedClock(Clock):
    def __init__(self, value: int) -> None:
        self._value = value

    def now(self) -> int:
        return self._value


T = TypeVar("T")


class ServiceLocator:
    _services: dict[type, object] = {}

    @classmethod
    def register(cls, service_type: Type[T], instance: T) -> None:
        cls._services[service_type] = instance

    @classmethod
    def get(cls, service_type: Type[T]) -> T:
        service = cls._services.get(service_type)
        if service is None:
            raise RuntimeError(f"Service non enregistré : {service_type.__name__}")
        return service  # type: ignore[return-value]


class TokenServiceWithLocator:
    def create_token(self, user: str) -> str:
        # Dépendance invisible dans le constructeur et l'API de la classe.
        timestamp = ServiceLocator.get(Clock).now()
        return f"{user}-{timestamp}"


class TokenServiceWithInjection:
    def __init__(self, clock: Clock) -> None:
        self._clock = clock

    def create_token(self, user: str) -> str:
        return f"{user}-{self._clock.now()}"


if __name__ == "__main__":
    print("=== Service Locator ===")
    ServiceLocator.register(Clock, SystemClock())
    print(TokenServiceWithLocator().create_token("alice"))

    print("\n=== Injection constructeur ===")
    fixed_clock = FixedClock(1_700_000_000_000)
    service = TokenServiceWithInjection(fixed_clock)
    print(service.create_token("alice"))
    print("Même entrée, même sortie : " + service.create_token("alice"))
