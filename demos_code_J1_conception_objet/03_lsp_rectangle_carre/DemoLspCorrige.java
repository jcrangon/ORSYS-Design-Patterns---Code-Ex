/**
 * Une correction possible : ne pas promettre les setters de Rectangle.
 * Rectangle et Carre sont deux formes immuables partageant une capacité commune.
 */
public class DemoLspCorrige {
    interface Forme {
        int aire();
    }

    record Rectangle(int largeur, int hauteur) implements Forme {
        public Rectangle {
            if (largeur <= 0 || hauteur <= 0) {
                throw new IllegalArgumentException("Dimensions invalides");
            }
        }

        @Override
        public int aire() {
            return largeur * hauteur;
        }
    }

    record Carre(int cote) implements Forme {
        public Carre {
            if (cote <= 0) {
                throw new IllegalArgumentException("Côté invalide");
            }
        }

        @Override
        public int aire() {
            return cote * cote;
        }
    }

    static void afficherAire(Forme f) {
        System.out.println(f.getClass().getSimpleName() + " : aire = " + f.aire());
    }

    public static void main(String[] args) {
        afficherAire(new Rectangle(10, 5));
        afficherAire(new Carre(5));
    }
}
