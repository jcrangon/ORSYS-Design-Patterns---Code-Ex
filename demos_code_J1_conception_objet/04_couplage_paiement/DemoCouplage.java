public class DemoCouplage {
    // Le contrat est exprimé dans le vocabulaire du client métier.
    interface PaiementPort {
        boolean payer(int cents);
    }

    // Simulation d'un SDK externe.
    static final class StripeClient {
        String charge(int cents) {
            System.out.println("[Stripe SDK simulé] charge " + cents + " cents");
            return "paid";
        }
    }

    // L'adaptateur traduit le SDK externe vers le contrat métier.
    static final class StripeAdapter implements PaiementPort {
        private final StripeClient stripe;

        StripeAdapter(StripeClient stripe) {
            this.stripe = stripe;
        }

        @Override
        public boolean payer(int cents) {
            return "paid".equals(stripe.charge(cents));
        }
    }

    // Fake pratique pour tester/démontrer sans fournisseur externe.
    static final class FakePaiement implements PaiementPort {
        private final boolean resultat;

        FakePaiement(boolean resultat) {
            this.resultat = resultat;
        }

        @Override
        public boolean payer(int cents) {
            System.out.println("[Fake] payer " + cents + " cents -> " + resultat);
            return resultat;
        }
    }

    static final class ValiderCommande {
        private final PaiementPort paiement;

        ValiderCommande(PaiementPort paiement) {
            this.paiement = paiement;
        }

        boolean executer(int totalCents) {
            return paiement.payer(totalCents);
        }
    }

    public static void main(String[] args) {
        ValiderCommande prod = new ValiderCommande(
            new StripeAdapter(new StripeClient())
        );
        System.out.println("Avec Stripe = " + prod.executer(4200));

        ValiderCommande test = new ValiderCommande(new FakePaiement(false));
        System.out.println("Avec Fake = " + test.executer(4200));
    }
}
