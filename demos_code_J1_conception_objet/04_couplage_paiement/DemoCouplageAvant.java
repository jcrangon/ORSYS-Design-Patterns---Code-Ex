/**
 * Simulation locale d'un SDK externe : aucune vraie dépendance réseau.
 */
public class DemoCouplageAvant {
    static final class StripeResponse {
        private final String status;
        StripeResponse(String status) { this.status = status; }
        String status() { return status; }
    }

    static final class StripeClient {
        StripeResponse charge(int cents) {
            System.out.println("[Stripe SDK simulé] charge " + cents + " cents");
            return new StripeResponse("paid");
        }
    }

    static final class ValiderCommande {
        private final StripeClient stripe = new StripeClient();

        boolean executer(int totalCents) {
            StripeResponse response = stripe.charge(totalCents);
            return "paid".equals(response.status());
        }
    }

    public static void main(String[] args) {
        boolean ok = new ValiderCommande().executer(4200);
        System.out.println("Paiement accepté = " + ok);
    }
}
