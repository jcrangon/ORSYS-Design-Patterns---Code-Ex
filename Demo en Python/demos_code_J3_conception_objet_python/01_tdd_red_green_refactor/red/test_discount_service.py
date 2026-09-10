from discount_service import DiscountService


def test_premium_customer_gets_fifteen_percent_discount():
    service = DiscountService()
    resultat = service.total_for(True, 200)
    assert resultat == 170
