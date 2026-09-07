public class DemoOcp {
    record Commande(int totalCents) {}

    interface CalculFraisLivraison {
        int calculer(Commande commande);
    }

    static final class Standard implements CalculFraisLivraison {
        public int calculer(Commande c) {
            return c.totalCents() >= 5000 ? 0 : 500;
        }
    }

    static final class Express implements CalculFraisLivraison {
        public int calculer(Commande c) {
            return 1200;
        }
    }

    static final class Relais implements CalculFraisLivraison {
        public int calculer(Commande c) {
            return 300;
        }
    }

    // Extension ajoutée sans modifier totalAvecLivraison().
    static final class Drone implements CalculFraisLivraison {
        public int calculer(Commande c) {
            return 1800;
        }
    }

    static int totalAvecLivraison(Commande c, CalculFraisLivraison calcul) {
        return c.totalCents() + calcul.calculer(c);
    }

    public static void main(String[] args) {
        Commande c = new Commande(4200);

        System.out.println("Standard -> " + totalAvecLivraison(c, new Standard()));
        System.out.println("Express  -> " + totalAvecLivraison(c, new Express()));
        System.out.println("Relais   -> " + totalAvecLivraison(c, new Relais()));
        System.out.println("Drone    -> " + totalAvecLivraison(c, new Drone()));
    }
}
