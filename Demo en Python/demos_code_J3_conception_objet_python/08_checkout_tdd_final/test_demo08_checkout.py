import pytest
from demo08_checkout import CheckoutService, Customer, Order, StandardDiscounts


class PaymentStub:
    def __init__(self, approved: bool):
        self._approved = approved
        self.authorized_amounts = []

    def authorize(self, amount: int) -> bool:
        self.authorized_amounts.append(amount)
        return self._approved


class InMemoryRepo:
    def __init__(self):
        self._ids = set()

    def save(self, order):
        self._ids.add(order.id)

    def exists(self, order_id):
        return order_id in self._ids


class NotifierSpy:
    def __init__(self):
        self.sent = []

    def confirmation(self, order_id):
        self.sent.append(order_id)


@pytest.fixture
def collaborators():
    # Une nouvelle instance pour chaque test, comme un @BeforeEach JUnit.
    return InMemoryRepo(), NotifierSpy()


def service_with_payment(approved, repo, notifier):
    return CheckoutService(PaymentStub(approved), repo, notifier, StandardDiscounts())


def test_confirms_order_when_payment_is_approved(collaborators):
    repo, notifier = collaborators
    service = service_with_payment(True, repo, notifier)
    receipt = service.confirm(Order("O1", Customer.STANDARD, 100))
    assert receipt.status == "APPROVED"
    assert receipt.charged == 100


def test_refuses_order_when_payment_is_declined(collaborators):
    repo, notifier = collaborators
    service = service_with_payment(False, repo, notifier)
    receipt = service.confirm(Order("O2", Customer.STANDARD, 100))
    assert receipt.status == "DECLINED"
    assert receipt.charged == 0
    assert not repo.exists("O2")


def test_sends_confirmation_when_approved(collaborators):
    repo, notifier = collaborators
    service = service_with_payment(True, repo, notifier)
    service.confirm(Order("O3", Customer.STANDARD, 100))
    assert notifier.sent == ["O3"]


def test_records_order_history_when_approved(collaborators):
    repo, notifier = collaborators
    service = service_with_payment(True, repo, notifier)
    service.confirm(Order("O4", Customer.STANDARD, 100))
    assert repo.exists("O4")


def test_applies_premium_discount_before_payment(collaborators):
    repo, notifier = collaborators
    service = service_with_payment(True, repo, notifier)
    receipt = service.confirm(Order("O5", Customer.PREMIUM, 200))
    assert receipt.charged == 170
