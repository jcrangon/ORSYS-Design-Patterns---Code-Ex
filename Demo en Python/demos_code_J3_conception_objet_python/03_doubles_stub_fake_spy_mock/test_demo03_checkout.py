from demo03_checkout import CheckoutService, Order


class PaymentStub:  # STUB : fournit une réponse prédéfinie
    def __init__(self, approved: bool):
        self._approved = approved

    def pay(self, order):
        return self._approved


class InMemoryOrderRepository:  # FAKE : implémentation simplifiée mais fonctionnelle
    def __init__(self):
        self._data = {}

    def save(self, order):
        self._data[order.id] = order

    def exists(self, order_id):
        return order_id in self._data


class NotifierSpy:  # SPY : enregistre les appels pour vérification
    def __init__(self):
        self.confirmations = []

    def confirmation(self, order):
        self.confirmations.append(order.id)


class AuditMock:  # MOCK manuel : sait lui-même si l'attente est satisfaite
    def __init__(self, expected):
        self._expected = expected
        self._seen = False

    def publish(self, event):
        self._seen = self._seen or event == self._expected

    def was_expected_event_published(self):
        return self._seen


def test_demonstrates_stub_fake_spy_and_manual_mock():
    payment = PaymentStub(True)
    repo = InMemoryOrderRepository()
    notifier = NotifierSpy()
    audit = AuditMock("ORDER_CONFIRMED")

    service = CheckoutService(payment, repo, notifier, audit)
    receipt = service.confirm(Order("O-42", 120))

    assert receipt.status == "APPROVED"
    assert repo.exists("O-42")
    assert notifier.confirmations == ["O-42"]
    assert audit.was_expected_event_published()
