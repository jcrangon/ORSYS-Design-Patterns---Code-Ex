public class DemoLsp {
    static class Rectangle {
        protected int largeur;
        protected int hauteur;

        void setLargeur(int largeur) { this.largeur = largeur; }
        void setHauteur(int hauteur) { this.hauteur = hauteur; }
        int aire() { return largeur * hauteur; }
    }

    static class Carre extends Rectangle {
        @Override
        void setLargeur(int cote) {
            super.setLargeur(cote);
            super.setHauteur(cote);
        }

        @Override
        void setHauteur(int cote) {
            super.setLargeur(cote);
            super.setHauteur(cote);
        }
    }

    static void scenarioClient(Rectangle r) {
        r.setLargeur(10);
        r.setHauteur(5);

        if (r.aire() != 50) {
            throw new AssertionError(
                "Le client attendait 50 mais obtient " + r.aire()
            );
        }
    }

    public static void main(String[] args) {
        scenarioClient(new Rectangle());
        System.out.println("Rectangle : OK");

        scenarioClient(new Carre()); // échoue
        System.out.println("Carre : OK");
    }
}
