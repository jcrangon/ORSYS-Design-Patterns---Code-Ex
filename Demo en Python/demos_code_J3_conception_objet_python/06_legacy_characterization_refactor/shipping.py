class LegacyShippingCalculator:
    def price(self, weight: int) -> int:
        if weight <= 0:
            return 0
        if weight < 5:
            return 7
        return 7 + (weight - 4) * 2


class RefactoredShippingCalculator:
    def price(self, weight: int) -> int:
        if weight <= 0:
            return 0
        base = 7
        excess_units = max(0, weight - 4)
        return base + excess_units * 2
