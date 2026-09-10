from discount_service import DiscountService


def test_premium_customer_gets_fifteen_percent_discount():
    service = DiscountService()
    assert service.total_for(True, 200) == 170


def test_standard_customer_pays_full_price():
    service = DiscountService()
    assert service.total_for(False, 200) == 200
