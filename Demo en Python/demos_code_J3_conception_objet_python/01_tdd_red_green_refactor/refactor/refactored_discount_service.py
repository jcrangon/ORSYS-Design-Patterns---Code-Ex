from discount_policy import DiscountPolicy


class DiscountService:
    def __init__(self, policy: DiscountPolicy):
        self._policy = policy

    def total_for(self, total: int) -> int:
        return self._policy.apply(total)
