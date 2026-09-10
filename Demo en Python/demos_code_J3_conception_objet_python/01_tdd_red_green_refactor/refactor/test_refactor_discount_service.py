from discount_policy import NoDiscountPolicy, PremiumPolicy
from refactored_discount_service import DiscountService


def test_premium_behavior_is_still_the_same_after_refactor():
    service = DiscountService(PremiumPolicy())
    assert service.total_for(200) == 170


def test_standard_behavior_is_still_the_same_after_refactor():
    service = DiscountService(NoDiscountPolicy())
    assert service.total_for(200) == 200
