class DiscountService:
    def total_for(self, premium: bool, total: int) -> int:
        if premium:
            return total * 85 // 100
        return total
