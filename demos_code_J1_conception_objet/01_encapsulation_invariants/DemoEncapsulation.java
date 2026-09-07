import java.util.ArrayList;
import java.util.List;

public class DemoEncapsulation {
    enum Statut { BROUILLON, PAYEE, EXPEDIEE }

    static final class Commande {
        private final List<Integer> prixCents = new ArrayList<>();
        private Statut statut = Statut.BROUILLON;

        void ajouterLigne(int prixCents) {
            if (statut != Statut.BROUILLON) {
                throw new IllegalStateException("Commande non modifiable");
            }
            if (prixCents <= 0) {
                throw new IllegalArgumentException("Prix invalide");
            }
            this.prixCents.add(prixCents);
        }

        int totalCents() {
            return prixCents.stream().mapToInt(Integer::intValue).sum();
        }

        void payer() {
            if (prixCents.isEmpty()) {
                throw new IllegalStateException("Commande vide");
            }
            if (statut != Statut.BROUILLON) {
                throw new IllegalStateException("Etat incompatible");
            }
            statut = Statut.PAYEE;
        }

        void expedier() {
            if (statut != Statut.PAYEE) {
                throw new IllegalStateException("Paiement requis");
            }
            statut = Statut.EXPEDIEE;
        }

        @Override
        public String toString() {
            return "Commande{total=" + totalCents() + " cents, statut=" + statut + "}";
        }
    }

    public static void main(String[] args) {
        Commande c = new Commande();
        c.ajouterLigne(1200);
        c.ajouterLigne(800);
        System.out.println(c);

        c.payer();
        c.expedier();
        System.out.println(c);

        // Décommenter pendant le cours pour provoquer l'erreur :
        // c.ajouterLigne(500);
    }
}
