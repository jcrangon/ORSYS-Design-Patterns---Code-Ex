import java.util.ArrayList;
import java.util.List;

/**
 * Version volontairement mauvaise : objet anémique et état modifiable librement.
 * À montrer rapidement avant la version protégée.
 */
public class DemoEncapsulationAvant {
    enum Statut { BROUILLON, PAYEE, EXPEDIEE }

    static final class Commande {
        public final List<Integer> prixCents = new ArrayList<>();
        public Statut statut = Statut.BROUILLON;
    }

    public static void main(String[] args) {
        Commande c = new Commande();
        c.prixCents.add(1200);
        c.prixCents.add(-500);       // incohérent, mais autorisé
        c.statut = Statut.EXPEDIEE;  // expédition sans paiement

        System.out.println("Prix = " + c.prixCents);
        System.out.println("Statut = " + c.statut);
        System.out.println("=> Le compilateur accepte un état métier incohérent.");
    }
}
