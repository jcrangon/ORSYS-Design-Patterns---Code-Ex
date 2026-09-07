import java.math.BigDecimal;

public class DemoDipAvant {
    static final class StripeSdk {
        String charge(int cents, String token) {
            return "stripe_tx_" + cents + "_" + token.substring(0, 4);
        }
    }

    static final class CheckoutService {
        // Le cas d'usage dépend DIRECTEMENT d'un détail fournisseur.
        private final StripeSdk stripe = new StripeSdk();

        String checkout(BigDecimal amount, String cardToken) {
            int cents = amount.movePointRight(2).intValueExact();
            String stripeId = stripe.charge(cents, cardToken);
            return "Paiement accepté : " + stripeId;
        }
    }

    public static void main(String[] args) {
        System.out.println(new CheckoutService().checkout(new BigDecimal("42.50"), "tok_demo_123"));
    }
}
