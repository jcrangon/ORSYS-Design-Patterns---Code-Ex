import java.math.BigDecimal;

public class DemoDip {
    // Le contrat est formulé depuis le BESOIN du cas d'usage.
    interface PaymentPort {
        PaymentReceipt authorize(Money amount, String paymentToken);
    }

    record Money(BigDecimal amount, String currency) { }
    record PaymentReceipt(String reference, boolean accepted) { }

    // Détail technique simulé.
    static final class StripeSdk {
        String charge(int cents, String token) {
            return "stripe_tx_" + cents + "_" + token.substring(0, 4);
        }
    }

    // Adapter : traduction entre le vocabulaire du coeur et Stripe.
    static final class StripePaymentAdapter implements PaymentPort {
        private final StripeSdk stripe;

        StripePaymentAdapter(StripeSdk stripe) {
            this.stripe = stripe;
        }

        @Override
        public PaymentReceipt authorize(Money amount, String paymentToken) {
            int cents = amount.amount().movePointRight(2).intValueExact();
            String id = stripe.charge(cents, paymentToken);
            return new PaymentReceipt(id, true);
        }
    }

    static final class CheckoutService {
        private final PaymentPort payment;

        CheckoutService(PaymentPort payment) {
            this.payment = payment;
        }

        String checkout(Money amount, String token) {
            PaymentReceipt receipt = payment.authorize(amount, token);
            return receipt.accepted()
                    ? "Paiement accepté : " + receipt.reference()
                    : "Paiement refusé";
        }
    }

    public static void main(String[] args) {
        PaymentPort productionPort = new StripePaymentAdapter(new StripeSdk());
        CheckoutService checkout = new CheckoutService(productionPort);
        System.out.println(checkout.checkout(new Money(new BigDecimal("42.50"), "EUR"), "tok_demo_123"));

        // Preuve pédagogique : le même cas d'usage fonctionne sans Stripe.
        PaymentPort fake = (amount, token) -> new PaymentReceipt("fake-001", true);
        System.out.println(new CheckoutService(fake)
                .checkout(new Money(new BigDecimal("10.00"), "EUR"), "test-token"));
    }
}
