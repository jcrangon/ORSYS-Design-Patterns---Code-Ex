from demo05_checkout import Checkout, Order


class MailerSpy:
    def __init__(self):
        self.sent_to = []

    def send_confirmation(self, email):
        self.sent_to.append(email)


def test_state_test_checks_the_business_result():
    mailer = MailerSpy()
    checkout = Checkout(mailer)
    receipt = checkout.confirm(Order("alice@example.test", 100))
    assert receipt.status == "APPROVED"


def test_interaction_test_checks_a_significant_external_effect():
    mailer = MailerSpy()
    checkout = Checkout(mailer)
    checkout.confirm(Order("alice@example.test", 100))
    assert mailer.sent_to == ["alice@example.test"]
