public class DemoOcpAvant {
    enum ModeLivraison { STANDARD, EXPRESS, RELAIS }
    record Commande(int totalCents) {}

    static int frais(Commande c, ModeLivraison mode) {
        return switch (mode) {
            case STANDARD -> c.totalCents() >= 5000 ? 0 : 500;
            case EXPRESS -> 1200;
            case RELAIS -> 300;
        };
    }

    static int totalAvecLivraison(Commande c, ModeLivraison mode) {
        return c.totalCents() + frais(c, mode);
    }

    public static void main(String[] args) {
        Commande c = new Commande(4200);
        for (ModeLivraison mode : ModeLivraison.values()) {
            System.out.println(mode + " -> " + totalAvecLivraison(c, mode));
        }
    }
}
