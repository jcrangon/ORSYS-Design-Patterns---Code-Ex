from abc import ABC, abstractmethod


class DiscountPolicy(ABC):
    @abstractmethod
    def apply(self, total: int) -> int:
        raise NotImplementedError


class NoDiscountPolicy(DiscountPolicy):
    def apply(self, total: int) -> int:
        return total


class PremiumPolicy(DiscountPolicy):
    def apply(self, total: int) -> int:
        return total * 85 // 100
